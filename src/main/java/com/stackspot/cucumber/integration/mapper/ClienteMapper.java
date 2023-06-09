package com.stackspot.cucumber.integration.mapper;

import com.stackspot.cucumber.integration.dto.ClienteDTO;
import com.stackspot.cucumber.integration.model.Cliente;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ClienteMapper {

    private final ContaMapper contaMapper;

    public Cliente fromDTO(ClienteDTO clienteDTO) {
        var cliente = new Cliente();
        cliente.setId(clienteDTO.getId());
        cliente.setNome(clienteDTO.getNome());
        cliente.setCpf(clienteDTO.getCpf());
        cliente.setEmail(clienteDTO.getEmail());
        cliente.setConta(contaMapper.toConta(clienteDTO.getConta()));
        return cliente;
    }

    public ClienteDTO toDTO(Cliente cliente) {
        return ClienteDTO.builder()
                .id(cliente.getId())
                .cpf(cliente.getCpf())
                .nome(cliente.getNome())
                .email(cliente.getEmail())
                .conta(contaMapper.fromConta(cliente.getConta()))
                .build();
    }
}
