package com.stackspot.cucumber.integration.model;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @Column(unique=true)
    private String cpf;
    @NotNull
    private String nome;
    @NotNull
    private String email;
    @ManyToOne
    private Conta conta;

}
