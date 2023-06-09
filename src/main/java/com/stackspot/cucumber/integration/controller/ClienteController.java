package com.stackspot.cucumber.integration.controller;

import com.stackspot.cucumber.integration.dto.ClienteDTO;
import com.stackspot.cucumber.integration.mapper.ClienteMapper;
import com.stackspot.cucumber.integration.service.ClienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1/clientes")
@RequiredArgsConstructor
public class ClienteController {

    private final ClienteService service;
    private final ClienteMapper mapper;

    @GetMapping
    @ResponseStatus(code = HttpStatus.OK)
    public List<ClienteDTO> getAll(){
        return service.getClientes().stream().map(mapper::toDTO).collect(Collectors.toList());
    }

    @GetMapping("/{cpf}")
    @ResponseStatus(code = HttpStatus.OK)
    public ClienteDTO getCliente(@NotNull @PathVariable("cpf") String cpf) {
        return mapper.toDTO(service.getCliente(cpf));
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public ClienteDTO createCliente(@Validated @RequestBody ClienteDTO clienteDTO) {
        var cliente = mapper.fromDTO(clienteDTO);
        return mapper.toDTO(service.saveCliente(cliente));
    }
    
}
