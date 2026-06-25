package com.sistema.bancario.entities;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_account")
@EqualsAndHashCode(of = "id")
public class Account  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private BigDecimal balance;

    @Column(unique = true)
    private String numberAccount;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

}
