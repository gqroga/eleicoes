package com.lifters.voter.registration.services;

import com.lifters.voter.registration.models.dtos.candidatos.CandidatosRequestAtualizarDto;
import com.lifters.voter.registration.models.dtos.candidatos.CandidatosRequestDto;
import com.lifters.voter.registration.models.dtos.candidatos.CandidatosResponseDto;
import com.lifters.voter.registration.models.dtos.candidatos.RelatorioDto;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.UUID;

public interface CandidatosService {

    CandidatosResponseDto criarCandidatos(CandidatosRequestDto candidatosRequestDto);

    CandidatosResponseDto buscarCandidatoPorId(UUID id);

    List<CandidatosResponseDto> buscarCandidatos();

    void deletarCandidato(UUID id);

    CandidatosResponseDto alterarCandidato(UUID id, CandidatosRequestAtualizarDto candidatosRequestAtualizarDto);

    List<RelatorioDto> relatorio();
}
