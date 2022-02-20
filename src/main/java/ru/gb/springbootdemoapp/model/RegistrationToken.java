package ru.gb.springbootdemoapp.model;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "registration_tokens")
@Data
@NoArgsConstructor
public class RegistrationToken {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column
  private Long id;

  @Column(name = "token")
  private String token;

  @Column(name = "expired_at")
  private LocalDateTime expiredAt;

  @ManyToOne
  @JoinColumn(name = "user_id")
  private AppUser appUser;

  public RegistrationToken(String token, LocalDateTime expiredAt, AppUser appUser) {
    this.token = token;
    this.expiredAt = expiredAt;
    this.appUser = appUser;
  }
}
