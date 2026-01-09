package com.semicolon.crudClientes.dto;

import com.semicolon.crudClientes.entities.Client;
import jakarta.validation.constraints.*;

import java.time.LocalDate;

public class ClientDTO {
    private Long id;
    @Size(min=3, max = 80, message = "The name must have between 3 and 80 characters.")
    @NotBlank(message = "The field 'name' must not be blank.")
    private String name;
    @NotBlank(message = "The field 'cpf' must not be blank.")
    private String cpf;
    @NotNull(message = "The field 'income' is required.")
    private Double income;
    @PastOrPresent(message = "The birthdate must be in the present or past.")
    private LocalDate birthDate;
    @NotNull(message = "The field 'children' must not be blank.")
    @PositiveOrZero(message = "Number of children cannot be negative")
    private Integer children;

    public ClientDTO(){}
    public ClientDTO(Client entity){
        this.id = entity.getId();;
        this.name = entity.getName();
        this.cpf = entity.getCpf();
        this.income = entity.getIncome();;
        this.birthDate = entity.getBirthDate();
        this.children = entity.getChildren();
    }

    public ClientDTO(Long id, String name, String cpf, Double income,
                  LocalDate birthDate, Integer children){
        this.id = id;
        this.name = name;
        this.cpf = cpf;
        this.income = income;
        this.birthDate = birthDate;
        this.children = children;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCpf() {
        return cpf;
    }

    public Double getIncome() {
        return income;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public Integer getChildren() {
        return children;
    }
}
