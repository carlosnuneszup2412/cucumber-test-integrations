package com.stackspot.cucumber.integration.model;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@Setter
public class Conta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private ContaStatus status;

    @NotNull
    private ContaTipo tipo;

    public enum ContaStatus {
        ATIVA,
        INATIVA,
        BLOQUEADA
    }
    public enum ContaTipo {
        CORRENTE,
        POUPANCA
    }

}
