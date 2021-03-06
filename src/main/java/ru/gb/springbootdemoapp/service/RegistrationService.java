package ru.gb.springbootdemoapp.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.gb.springbootdemoapp.controller.exceptions.NoPasswordException;
import ru.gb.springbootdemoapp.model.AppUser;
import ru.gb.springbootdemoapp.model.RegistrationToken;
import ru.gb.springbootdemoapp.repository.AuthorityRepository;
import ru.gb.springbootdemoapp.repository.RegistrationTokenRepository;
import ru.gb.springbootdemoapp.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import static ru.gb.springbootdemoapp.model.EmailType.USER_REGISTRATION;

@Service
public class RegistrationService {

  private final UserRepository userRepository;
  private final BCryptPasswordEncoder bCryptPasswordEncoder;
  private final AuthorityRepository authorityRepository;
  private final RegistrationTokenRepository registrationTokenRepository;
  private final EmailService emailService;

  public RegistrationService(UserRepository userRepository,
                             BCryptPasswordEncoder bCryptPasswordEncoder,
                             AuthorityRepository authorityRepository,
                             RegistrationTokenRepository registrationTokenRepository,
                             EmailService emailService) {
    this.userRepository = userRepository;
    this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    this.authorityRepository = authorityRepository;
    this.registrationTokenRepository = registrationTokenRepository;
    this.emailService = emailService;
  }

  @Transactional
  public String sighUp(String email, String password, String repeat) {
    if (!password.equals(repeat)){
      throw new NoPasswordException("Пароли не совпадают");
    }
    boolean userExist = userRepository.findByEmail(email).isPresent();
    if (userExist) {
      throw new IllegalStateException("Пользователь уже существует");
    }
    var user = new AppUser();
    user.setEmail(email);
    user.setPassword(bCryptPasswordEncoder.encode(password));
    user.setEnabled(false);
    user.setAuthorities(Set.of(authorityRepository.findByName("ROLE_USER")));
    userRepository.save(user);

    String tokenUid = UUID.randomUUID().toString();
    registrationTokenRepository.save(new RegistrationToken(tokenUid, LocalDateTime.now().plusMinutes(15), user));

    emailService.sendMail(USER_REGISTRATION, Map.of("token", tokenUid), List.of(email));

    return tokenUid;
  }

  @Transactional
  public Integer confirmRegistration(String token) {
    var user = registrationTokenRepository.findUserByToken(token);
    var registToken = registrationTokenRepository.findByToken(token);
    if (user.isEmpty() || registToken.isEmpty()) {
      return -1;
    }
    if(LocalDateTime.now().isAfter(registToken.get().getExpiredAt())){
      RegistrationToken registrationToken = registToken.get();
      registrationToken.setToken(UUID.randomUUID().toString());
      registrationTokenRepository.save(registrationToken);
      emailService.sendMail(USER_REGISTRATION, Map.of("token", registrationToken.getToken()), List.of(user.get().getEmail()));
      return 0;
    }
    user.ifPresent(u -> u.setEnabled(true));
    return 1;
  }
}