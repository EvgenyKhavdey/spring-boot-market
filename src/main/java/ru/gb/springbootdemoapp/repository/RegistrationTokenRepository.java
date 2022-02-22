package ru.gb.springbootdemoapp.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.gb.springbootdemoapp.model.AppUser;
import ru.gb.springbootdemoapp.model.RegistrationToken;

import java.util.Optional;

@Repository
public interface RegistrationTokenRepository extends JpaRepository<RegistrationToken, Long> {

  @Query("SELECT rt.appUser FROM RegistrationToken rt WHERE rt.token = :token")
  Optional<AppUser> findUserByToken(@Param("token") String token);

  Optional<RegistrationToken> findByToken(String token);
}
