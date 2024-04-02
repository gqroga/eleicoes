package com.lifters.voter.registration.models.dtos.eleitores;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class EleitoresRequestAtualizarDto {
    private UUID id;
    @NotBlank(message = "Preenchimento Obrigatório")
    private String nome;
    @CPF
    @NotBlank(message = "Preenchimento Obrigatório")
    private String cpf;
}
