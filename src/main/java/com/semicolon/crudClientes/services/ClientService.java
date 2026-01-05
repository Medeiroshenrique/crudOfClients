package com.semicolon.crudClientes.services;

import com.semicolon.crudClientes.dto.ClientDTO;
import com.semicolon.crudClientes.entities.Client;
import com.semicolon.crudClientes.repositories.ClientRepository;
import com.semicolon.crudClientes.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class ClientService {
    @Autowired
    private ClientRepository repository;

    @Transactional(readOnly = true)
    public ClientDTO findById(Long id){
        Optional<Client> result = repository.findById(id);
        Client client = result.orElseThrow(()-> new ResourceNotFoundException("This resource was not found!"));

        ClientDTO dto = new ClientDTO(client);
        return dto;
    }

    public ClientDTO insert(ClientDTO dto){
        try{
        Client entity = new Client();
        copyToEntity(dto, entity);

        entity = repository.save(entity);
        return new ClientDTO(entity);
        } catch(EntityNotFoundException e){
            throw new ResourceNotFoundException("This resource was not found!");
        }
    }

    private void copyToEntity(ClientDTO dto, Client entity){
        entity.setName(dto.getName());
        entity.setCpf(dto.getCpf());
        entity.setIncome(dto.getIncome());
        entity.setBirthDate(dto.getBirthDate());
        entity.setChildren(dto.getChildren());
    }
}
