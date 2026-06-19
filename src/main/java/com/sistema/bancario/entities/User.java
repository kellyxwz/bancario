package com.sistema.bancario.entities;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "db_user")
@NoArgsConstructor//constructor padre
@AllArgsConstructor//constructor complete parameters
@EqualsAndHashCode(of = "id")
public class User {

 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 private Long id;

 @Column(nullable = false, length = 150)
 private String name;

 @Column(nullable = false)
 private String email;
 private Long phone;

 @Column(nullable = false, unique = true)
 private String password;

 public User(String email, @NonNull String password) {
  this.email = email;
  this.password = password;
 }


}
