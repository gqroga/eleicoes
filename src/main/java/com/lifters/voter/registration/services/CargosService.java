package com.lifters.voter.registration.services;

import com.lifters.voter.registration.models.dtos.cargos.CargosRequestAtualizarDto;
import com.lifters.voter.registration.models.dtos.cargos.CargosRequestDto;
import com.lifters.voter.registration.models.dtos.cargos.CargosResponseDto;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.UUID;


public interface CargosService {


    CargosResponseDto criarCargo(CargosRequestDto cargosRequestDto);

    CargosResponseDto buscarPorId(UUID id);

    List<CargosResponseDto> buscarCargos();


    void deletar(UUID id);

    @Transactional
    CargosResponseDto alterarCargo(UUID id, CargosRequestAtualizarDto cargosRequestAtualizarDto);

}
