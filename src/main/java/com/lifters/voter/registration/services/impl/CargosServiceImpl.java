package com.lifters.voter.registration.services.impl;

import com.lifters.voter.registration.exceptions.CargosException;
import com.lifters.voter.registration.models.CargoModel;
import com.lifters.voter.registration.models.VotoModel;
import com.lifters.voter.registration.models.dtos.cargos.CargosRequestAtualizarDto;
import com.lifters.voter.registration.models.dtos.cargos.CargosRequestDto;
import com.lifters.voter.registration.models.dtos.cargos.CargosResponseDto;
import com.lifters.voter.registration.repositories.CargosRepository;
import com.lifters.voter.registration.repositories.VotoRepository;
import com.lifters.voter.registration.services.CargosService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class CargosServiceImpl implements CargosService {

    @Autowired
    private  ModelMapper modelMapper;

    private  CargosRepository cargosRepository;

    @Override
    public CargosResponseDto criarCargo(CargosRequestDto cargosRequestDto) {
        CargoModel cargoModel = modelMapper.map(cargosRequestDto, CargoModel.class);
        cargoModel.setCriadoEm(LocalDateTime.now());
        cargosRepository.save(cargoModel);
        return modelMapper.map(cargoModel, CargosResponseDto.class);
    }

    @Transactional
    @Override
    public void deletar(UUID id) {
        cargosRepository.deleteById(id);
    }

    @Override
    public CargosResponseDto buscarPorId(UUID id) {
        Optional<CargoModel> cargosModel = cargosRepository.findById(id);
        if(cargosModel.isEmpty()){
            throw new CargosException("Cargo não encontrado");
        }
        return modelMapper.map(cargosModel.get(), CargosResponseDto.class);
    }

    @Override
    public List<CargosResponseDto> buscarCargos() {
        List<CargoModel> cargosModelList = cargosRepository.findAll();
        return cargosModelList.stream()
                .map(cargosModel -> modelMapper.map(cargosModel, CargosResponseDto.class))
                .toList();
    }

    @Transactional
    @Override
    public CargosResponseDto alterarCargo(UUID id, CargosRequestAtualizarDto cargosRequestAtualizarDto) {
        CargoModel cargosModel = modelMapper.map(buscarPorId(id), CargoModel.class);
        atualizarCargo(cargosRequestAtualizarDto, cargosModel);
        cargosRepository.save(cargosModel);
        return modelMapper.map(cargosModel, CargosResponseDto.class);
    }

    private void atualizarCargo(CargosRequestAtualizarDto cargosRequestAtualizarDto, CargoModel cargosModel) {
        if (cargosRequestAtualizarDto.getNome() != null) {
            cargosModel.setNome(cargosRequestAtualizarDto.getNome());
            cargosModel.setAlteradoEm(LocalDateTime.now());
        }
    }
}

