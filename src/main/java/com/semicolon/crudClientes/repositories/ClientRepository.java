package com.semicolon.crudClientes.repositories;

import com.semicolon.crudClientes.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository <Client, Long>{
}
