package com.semicolon.crudClientes.services;

import com.semicolon.crudClientes.dto.ClientDTO;
import com.semicolon.crudClientes.entities.Client;
import com.semicolon.crudClientes.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class ClientService {
    @Autowired
    private ClientRepository repository;

    @Transactional
    public ClientDTO findById(Long id){
        Optional<Client> result = repository.findById(id);
        Client client = result.orElseThrow(()-> ResourceNotFoundException("This resource was not found!"));

        ClientDTO dto = new ClientDTO(client);
        result
    }
}
