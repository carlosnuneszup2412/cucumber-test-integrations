package com.stackspot.cucumber.integration.service;

import com.stackspot.cucumber.integration.exception.ClienteAlreadyExist;
import com.stackspot.cucumber.integration.exception.ClienteNotFound;
import com.stackspot.cucumber.integration.model.Cliente;
import com.stackspot.cucumber.integration.repository.ClienteRepository;
import com.stackspot.cucumber.integration.repository.ContaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClienteService {

    private final ClienteRepository clienteRepository;
    private final ContaRepository contaRepository;

    public List<Cliente> getClientes() {
        return this.clienteRepository.findAll();
    }

    public Cliente getCliente(Long id) {
        return this.clienteRepository.findById(id).orElseThrow(ClienteNotFound::new);
    }

    public Cliente getCliente(String cpf) {
        return this.clienteRepository.findByCpf(cpf).orElseThrow(ClienteNotFound::new);
    }

    public Cliente saveCliente(Cliente cliente) {
        var found = clienteRepository.findByCpf(cliente.getCpf()).stream().findFirst();
        if (found.isPresent()) throw new ClienteAlreadyExist();
        contaRepository.save(cliente.getConta());
        return clienteRepository.save(cliente);
    }
}
