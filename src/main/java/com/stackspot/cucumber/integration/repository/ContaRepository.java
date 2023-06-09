package com.stackspot.cucumber.integration.repository;

import com.stackspot.cucumber.integration.model.Conta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContaRepository extends JpaRepository<Conta, Long> {
}
