package com.stackspot.cucumber.integration.mapper;

import com.stackspot.cucumber.integration.dto.ContaDTO;
import com.stackspot.cucumber.integration.model.Conta;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ContaMapper {

    public Conta toConta(ContaDTO contaDTO) {
        var conta = new Conta();
        conta.setTipo(contaDTO.getTipo());
        conta.setStatus(contaDTO.getStatus());
        return conta;
    }

    public ContaDTO fromConta(Conta conta) {
        return ContaDTO.builder()
                .status(conta.getStatus())
                .tipo(conta.getTipo())
                .build();
    }
}
