package ru.gb.springbootdemoapp.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.gb.springbootdemoapp.controller.exceptions.NoPasswordException;
import ru.gb.springbootdemoapp.model.RegistrationToken;
import ru.gb.springbootdemoapp.repository.AuthorityRepository;
import ru.gb.springbootdemoapp.repository.RegistrationTokenRepository;
import ru.gb.springbootdemoapp.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@Service
public class RegisterService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final AuthorityRepository authorityRepository;
    private final RegistrationTokenRepository registrationTokenRepository;
    private final EmailService emailService;

    public RegisterService(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder,
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
        var user = new ru.gb.springbootdemoapp.model.User();
        user.setEmail(email);
        user.setPassword(bCryptPasswordEncoder.encode(password));
        user.setEnabled(false);
        user.setAuthorities(Set.of(authorityRepository.findByName("ROLE_USER")));
        userRepository.save(user);

        String tokenUid = UUID.randomUUID().toString();
        registrationTokenRepository.save(new RegistrationToken(tokenUid, LocalDateTime.now().plusMinutes(15), user));

        emailService.sendVarificationLink(email, tokenUid);

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
            emailService.sendVarificationLink(user.get().getEmail(), registrationToken.getToken());
            return 0;
        }
        user.ifPresent(u -> u.setEnabled(true));
        return 1;
    }
}
