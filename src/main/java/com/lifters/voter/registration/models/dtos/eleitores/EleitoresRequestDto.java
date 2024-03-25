package com.lifters.voter.registration.models.dtos.eleitores;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class EleitoresRequestDto {
    @NotNull(message = "Preenchimento Obrigatório")
    private String nome;

    @CPF
    @NotNull(message = "Preenchimento Obrigatório")
    private String cpf;

    @NotNull(message = "Preenchimento Obrigatório")
    private UUID idCargo;
}
