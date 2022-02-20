package ru.gb.springbootdemoapp.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.gb.springbootdemoapp.model.AppUser;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<AppUser, Long> {

  Optional<AppUser> findByEmail(String email);

  @Query("FROM AppUser u JOIN FETCH u.authorities")
  List<AppUser> findAllFetchAuthority();
}
