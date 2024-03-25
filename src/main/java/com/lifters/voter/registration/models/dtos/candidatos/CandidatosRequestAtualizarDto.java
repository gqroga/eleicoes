package com.lifters.voter.registration.models.dtos.candidatos;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CandidatosRequestAtualizarDto {
    private UUID id;
    @NotNull(message = "Preenchimento obrigatório.")
    private String nome;
    @NotNull(message = "Preenchimento obrigatório.")
    private Integer numero;
    @NotNull(message = "Preenchimento obrigatório.")
    private String legenda;
    @NotNull(message = "Preenchimento obrigatório.")
    private String cargo;
}
