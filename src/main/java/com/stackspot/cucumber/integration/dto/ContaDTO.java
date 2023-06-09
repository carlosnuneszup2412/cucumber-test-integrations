package com.stackspot.cucumber.integration.dto;

import com.stackspot.cucumber.integration.model.Conta;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ContaDTO {

    private Long id;

    @NotNull
    @Builder.Default
    private Conta.ContaStatus status = Conta.ContaStatus.ATIVA;

    @NotNull
    @Builder.Default
    private Conta.ContaTipo tipo = Conta.ContaTipo.CORRENTE;

}
