package com.sistema.bancario.entities;

import jakarta.persistence.*;
import lombok.*;
import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "db_user")
@NoArgsConstructor//constructor padre
@AllArgsConstructor//constructor complete parameters
@EqualsAndHashCode(of = "id")
public class User implements UserDetails {

 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 private Long id;

 @NonNull
 private String name;
 private String email;
 private Long phone;
 @NonNull
 private String password;

 @Override
 public Collection<? extends GrantedAuthority> getAuthorities() {
  return List.of(new SimpleGrantedAuthority("ROLE_CLIENT"));
 }

 @Override
 public @Nullable String getPassword() {
  return "";
 }

 @Override
 public String getUsername() {
  return email;
 }

 @Override
 public boolean isAccountNonExpired() {
  return true;
 }

 @Override
 public boolean isAccountNonLocked() {
  return true;    }

 @Override
 public boolean isCredentialsNonExpired() {
  return true;    }

 @Override
 public boolean isEnabled() {
  return true;    }


}
