package com.mballem.demo_park_api.web.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ClienteCreateDto {

    @NotNull
    @Size(min = 3, max = 100)
    private String nome;

    @Size(min = 11, max = 11)
    @CPF
    private String cpf;

}
