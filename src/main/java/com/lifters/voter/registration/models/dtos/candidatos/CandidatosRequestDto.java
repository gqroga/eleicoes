package com.lifters.voter.registration.models.dtos.candidatos;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CandidatosRequestDto {
    @NotNull(message = "Preenchimento obrigatório.")
    private String nome;
    @NotNull(message = "Preenchimento obrigatório.")
    private Integer numero;
    @NotNull(message = "Preenchimento obrigatório.")
    private String legenda;
    @NotNull(message = "Preenchimento obrigatório.")
    private String cargo;
}

