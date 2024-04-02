package com.lifters.voter.registration.services.impl;

import com.lifters.voter.registration.exceptions.*;
import com.lifters.voter.registration.models.CandidatoModel;
import com.lifters.voter.registration.models.CargoModel;
import com.lifters.voter.registration.models.EleitorModel;
import com.lifters.voter.registration.models.VotoModel;
import com.lifters.voter.registration.models.dtos.eleitores.EleitoresRequestAtualizarDto;
import com.lifters.voter.registration.models.dtos.eleitores.EleitoresRequestDto;
import com.lifters.voter.registration.models.dtos.eleitores.EleitoresResponseDto;
import com.lifters.voter.registration.models.dtos.eleitores.VotarDTO;
import com.lifters.voter.registration.repositories.CandidatosRepository;
import com.lifters.voter.registration.repositories.CargosRepository;
import com.lifters.voter.registration.repositories.EleitoresRepository;
import com.lifters.voter.registration.repositories.VotoRepository;
import com.lifters.voter.registration.services.EleitoresService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Supplier;

@Service
@AllArgsConstructor
public class EleitoresServiceImpl implements EleitoresService {

    @Autowired
    private  ModelMapper modelMapper;

    private  EleitoresRepository eleitoresRepository;

    private VotoRepository votoRepository;

    private CandidatosRepository candidatosRepository;

    @Override
    public EleitoresResponseDto criarEleitores(EleitoresRequestDto eleitoresRequestDto) {

        existsByCpf(eleitoresRequestDto.getCpf());

        EleitorModel eleitoresModel = modelMapper.map(eleitoresRequestDto, EleitorModel.class);
        eleitoresModel.setCriadoEm(LocalDateTime.now());
        eleitoresRepository.save(eleitoresModel);
        return modelMapper.map(eleitoresModel, EleitoresResponseDto.class);
    }

    @Override
    public EleitoresResponseDto buscarEleitorPorId(UUID id) {
        Optional<EleitorModel> eleitoresModel = eleitoresRepository.findById(id);
        if(eleitoresModel.isEmpty()){
            throw new EleitoresException("Eleitor não encontrado.");
        }
        return modelMapper.map(eleitoresModel.get(), EleitoresResponseDto.class);
    }

    @Override
    public List<EleitoresResponseDto> buscarEleitores() {
        List<EleitorModel> eleitoresModelList = eleitoresRepository.findAll();
        return eleitoresModelList.stream().map(eleitoresModel -> modelMapper.map(eleitoresModel, EleitoresResponseDto.class)).toList();
    }

    @Transactional
    @Override
    public void deletarEleitor(UUID id) {
        buscarEleitorPorId(id);
        VotoModel votoByIdEleitor = votoRepository.findVotoByEleitorModelId(id);
        if (votoByIdEleitor != null) {
            throw new EleitorComVotoException("O eleitor não pode ser excluído pois já possui um voto registrado");
        }

        eleitoresRepository.deleteById(id);
    }

    @Transactional
    @Override
    public EleitoresResponseDto alterarEleitor(UUID id, EleitoresRequestAtualizarDto eleitoresRequestAtualizarDto) {
        EleitorModel eleitoresModel = modelMapper.map(buscarEleitorPorId(id), EleitorModel.class);
        atualizarEleitor(eleitoresRequestAtualizarDto, eleitoresModel);
        eleitoresRepository.save(eleitoresModel);
        return modelMapper.map(eleitoresModel, EleitoresResponseDto.class);
    }

    @Override
    public void  votar(UUID id, VotarDTO votarDTO) {

        buscarEleitorPorId(id);

        Optional<CandidatoModel> candidato = candidatosRepository.findById(votarDTO.getIdCandidato());
        if (candidato.isEmpty()){
            throw new CandidatosException("Candidato não encontrado");

        }

        Optional<EleitorModel> eleitor = eleitoresRepository.findById(votarDTO.getIdEleitor());
        if (eleitor.isEmpty()) {
            throw  new EleitoresException("Eleitor não encontrado.");
        }

        VotoModel votoModel = new VotoModel();
        votoModel.setData(LocalDateTime.now());
        votoModel.setCandidatoModel(modelMapper.map(candidato, CandidatoModel.class));
        votoModel.setEleitorModel(modelMapper.map(eleitor, EleitorModel.class));

        votoRepository.save(votoModel);

    }

    private void atualizarEleitor(EleitoresRequestAtualizarDto eleitoresRequestAtualizarDto, EleitorModel eleitoresModel){
        if(eleitoresRequestAtualizarDto.getNome() != null) {
            eleitoresModel.setNome(eleitoresRequestAtualizarDto.getNome());
            eleitoresModel.setCpf(eleitoresRequestAtualizarDto.getCpf());
            eleitoresModel.setAlteradoEm(LocalDateTime.now());
        }
    }

    private void existsByCpf (String cpf) {
        if(eleitoresRepository.existsByCpf(cpf)) {
            throw new CpfCadastradoException("CPF já cadastrado.");
        }
    }


}
