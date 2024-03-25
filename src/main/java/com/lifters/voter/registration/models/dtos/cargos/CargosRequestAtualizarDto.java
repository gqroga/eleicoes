package com.lifters.voter.registration.models.dtos.cargos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.validation.constraints.NotBlank;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CargosRequestAtualizarDto {
    @NotBlank(message = "Preenchimento Obrigatório.")
    private String nome;
}
