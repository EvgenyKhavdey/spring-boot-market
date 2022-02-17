package ru.gb.springbootdemoapp.service;


import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.gb.springbootdemoapp.model.Authority;
import ru.gb.springbootdemoapp.repository.UserRepository;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService {

  private final UserRepository userRepository;
  private final BCryptPasswordEncoder bCryptPasswordEncoder;

  public UserService(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
    this.userRepository = userRepository;
    this.bCryptPasswordEncoder = bCryptPasswordEncoder;
  }

  @Override
  @Transactional(readOnly = true)
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    return userRepository.findByLogin(username)
        .map(user -> new User(
            user.getLogin(),
            user.getPassword(),
            user.getAuthorities().stream().map(authority -> new SimpleGrantedAuthority(authority.getName())).collect(Collectors.toSet())
            )
        ).orElseThrow(() -> new UsernameNotFoundException("Пользователь не найден"));
  }

  @Transactional
  public void saveUser(String username, String password, String email) {
    var user = new ru.gb.springbootdemoapp.model.User();
    user.setLogin(username);
    user.setPassword(bCryptPasswordEncoder.encode(password));
    user.setEmail(email);
    user.setEnabled(false);
    user.setAuthorities(Set.of(new Authority("ROLE_USER")));
    userRepository.save(user);
  }
}
