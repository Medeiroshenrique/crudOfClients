package com.semicolon.crudClientes.services;

import com.semicolon.crudClientes.dto.ClientDTO;
import com.semicolon.crudClientes.entities.Client;
import com.semicolon.crudClientes.repositories.ClientRepository;
import com.semicolon.crudClientes.services.exceptions.DatabaseException;
import com.semicolon.crudClientes.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
        Client client = result.orElseThrow(()-> new ResourceNotFoundException("This client was not found!"));

        ClientDTO dto = new ClientDTO(client);
        return dto;
    }

    @Transactional(readOnly = true)
    public Page<ClientDTO> findAll(Pageable pageable){
        Page<Client> result = repository.findAll(pageable);

        return result.map(x-> new ClientDTO(x));
    }

    @Transactional
    public ClientDTO insert(ClientDTO dto){
        Client entity = new Client();
        copyToEntity(dto, entity);

        entity = repository.save(entity);
        return new ClientDTO(entity);
    }

    private void copyToEntity(ClientDTO dto, Client entity){
        entity.setName(dto.getName());
        entity.setCpf(dto.getCpf());
        entity.setIncome(dto.getIncome());
        entity.setBirthDate(dto.getBirthDate());
        entity.setChildren(dto.getChildren());
    }

    @Transactional
    public ClientDTO update(Long id, ClientDTO dto){
        try{
        Client entity = repository.getReferenceById(id);
        copyToEntity(dto, entity);

        entity = repository.save(entity);
        return new ClientDTO(entity);
        }catch (EntityNotFoundException e){
            throw new EntityNotFoundException("This Client was not found in our database!");
        }
    }

}
