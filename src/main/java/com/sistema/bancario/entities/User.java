package com.sistema.bancario.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity
@Table(name = "db_user")
@Data
@NoArgsConstructor//constructor padre
@AllArgsConstructor//constructor complete parameters
public class User implements Serializable {
 public static final long serialVersionUID = 1L;

 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 private Long id;

 private String name;
 private String email;


}
