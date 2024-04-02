package com.lifters.voter.registration.services.impl;

import com.lifters.voter.registration.exceptions.CandidatoComVotoException;
import com.lifters.voter.registration.exceptions.CandidatosException;
import com.lifters.voter.registration.exceptions.CargosException;
import com.lifters.voter.registration.exceptions.NomeCadastradoException;
import com.lifters.voter.registration.models.CandidatoModel;
import com.lifters.voter.registration.models.VotoModel;
import com.lifters.voter.registration.models.dtos.candidatos.CandidatosRequestAtualizarDto;
import com.lifters.voter.registration.models.dtos.candidatos.CandidatosRequestDto;
import com.lifters.voter.registration.models.dtos.candidatos.CandidatosResponseDto;
import com.lifters.voter.registration.models.dtos.candidatos.RelatorioDto;
import com.lifters.voter.registration.repositories.CandidatosRepository;
import com.lifters.voter.registration.repositories.VotoRepository;
import com.lifters.voter.registration.services.CandidatosService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class CandidatosServiceImpl implements CandidatosService {

    @Autowired
    private ModelMapper modelMapper;

    private CandidatosRepository candidatosRepository;

    private VotoRepository votoRepository;


    @Override
    @Transactional
    public CandidatosResponseDto criarCandidatos(CandidatosRequestDto candidatosRequestDto) {

        existsByNome(candidatosRequestDto.getNome());

        CandidatoModel candidatosModel = modelMapper.map(candidatosRequestDto, CandidatoModel.class);
        candidatosModel.setCriadoEm(LocalDateTime.now());
        candidatosRepository.save(candidatosModel);
        return modelMapper.map(candidatosModel, CandidatosResponseDto.class);
    }

    @Override
    public CandidatosResponseDto buscarCandidatoPorId(UUID id) {
        Optional<CandidatoModel> candidatosModel = candidatosRepository.findById(id);
        if (!candidatosModel.isPresent()) {
            throw new CandidatosException("Candidato não encontrado.");
        }
        return modelMapper.map(candidatosModel.get(), CandidatosResponseDto.class);
    }

    @Override
    public List<CandidatosResponseDto> buscarCandidatos() {
        List<CandidatoModel> candidatosModelList = candidatosRepository.findAll();
        return candidatosModelList.stream().map(candidatosModel -> modelMapper.map(candidatosModel, CandidatosResponseDto.class)).toList();
    }

    @Override
    public void deletarCandidato(UUID id) {
        VotoModel votoByIdCandidato = votoRepository.findVotoByCandidatoModelId(id);
        if (votoByIdCandidato != null) {
            throw new CandidatoComVotoException("O candidato não pode ser excluído pois já possui um voto registrado.");
        }
        candidatosRepository.deleteById(id);
    }

    @Override
    public CandidatosResponseDto alterarCandidato(UUID id, CandidatosRequestAtualizarDto candidatosRequestAtualizarDto) {
        CandidatoModel candidatosModel = modelMapper.map(buscarCandidatoPorId(id), CandidatoModel.class);
        atualizarCandidato(candidatosRequestAtualizarDto, candidatosModel);
        candidatosRepository.save(candidatosModel);
        return modelMapper.map(candidatosModel, CandidatosResponseDto.class);
    }

    @Override
    public List<RelatorioDto> relatorio() {

        List<RelatorioDto> listaRelatorio = new ArrayList<>();

        List<Object[]> resultados = candidatosRepository.findCandidatosComMaiorVotoPorCargo();

        for (Object[] resultado : resultados) {
            UUID idCargo = (UUID) resultado[0];
            String nomeCargo = (String) resultado[1];
            Long qtdVotos = (Long) resultado[2];
            UUID idCandidato = (UUID) resultado[3];
            String nomeCandidato = (String) resultado[4];

            RelatorioDto relatorioDto = new RelatorioDto();
            relatorioDto.setIdCargo(idCargo);
            relatorioDto.setNomeCargo(nomeCargo);
            relatorioDto.setQtdVotos(qtdVotos);
            relatorioDto.setIdCandidatoVencedor(idCandidato);
            relatorioDto.setNomeCandidatoVencedor(nomeCandidato);

            listaRelatorio.add(relatorioDto);
        }

        return listaRelatorio;
    }

    private void atualizarCandidato(CandidatosRequestAtualizarDto candidatosRequestAtualizarDto, CandidatoModel candidatosModel) {
        if (candidatosRequestAtualizarDto.getNome() != null)
            candidatosModel.setNome(candidatosRequestAtualizarDto.getNome());
        if (candidatosRequestAtualizarDto.getNumero() != null)
            candidatosModel.setNumero(candidatosRequestAtualizarDto.getNumero());
        if (candidatosRequestAtualizarDto.getLegenda() != null)
            candidatosModel.setLegenda(candidatosRequestAtualizarDto.getLegenda());
        candidatosModel.setAlteradoEm(LocalDateTime.now());
    }

    private void existsByNome (String nome) {
        if(candidatosRepository.existsByNome(nome)) {
            throw new NomeCadastradoException("Nome já cadastrado.");
        }
    }

}