package com.lifters.voter.registration.models.dtos.eleitores;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VotarDTO {

    @NotNull(message = "Preenchimento obrigatório.")
    private UUID idCandidato;

    @NotNull(message = "Preenchimento obrigatório.")
    private UUID idEleitor;
}
