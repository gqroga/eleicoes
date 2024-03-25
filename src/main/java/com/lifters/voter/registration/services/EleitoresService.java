package com.lifters.voter.registration.services;

import com.lifters.voter.registration.models.dtos.eleitores.EleitoresRequestAtualizarDto;
import com.lifters.voter.registration.models.dtos.eleitores.EleitoresRequestDto;
import com.lifters.voter.registration.models.dtos.eleitores.EleitoresResponseDto;
import com.lifters.voter.registration.models.dtos.eleitores.VotarDTO;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.UUID;

public interface EleitoresService {


    EleitoresResponseDto criarEleitores(EleitoresRequestDto eleitoresRequestDto);

    EleitoresResponseDto buscarEleitorPorId(UUID id);

    List<EleitoresResponseDto> buscarEleitores();

    void deletarEleitor(UUID id);

    @Transactional
    EleitoresResponseDto alterarEleitor(UUID id, EleitoresRequestAtualizarDto eleitoresRequestAtualizarDto);

    void votar(UUID id, VotarDTO votarDTO);
}
