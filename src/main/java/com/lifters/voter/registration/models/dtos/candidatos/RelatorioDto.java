package com.lifters.voter.registration.models.dtos.candidatos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter

public class RelatorioDto {

    private UUID idCargo;

    private String nomeCargo;

    private Long votos;

    private UUID idCandidatoVencedor;

    private String nomeCandidatoVencedor;



}
