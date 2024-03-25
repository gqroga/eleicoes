package com.lifters.voter.registration.models.dtos.cargos;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CargosRequestDto {
    @NotBlank(message = "Preenchimento obrigatório.")
    private String nome;
}
