package com.sistema.bancario.entities;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@Entity
@Table(name = "db_user")
@NoArgsConstructor//constructor padre
@AllArgsConstructor//constructor complete parameters
@EqualsAndHashCode(of = "id")
public class User implements Serializable {
 public static final long serialVersionUID = 1L;

 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 private Long id;

 @NonNull
 private String name;
 private String email;
 private Long phone;
 @NonNull
 private String password;


}
