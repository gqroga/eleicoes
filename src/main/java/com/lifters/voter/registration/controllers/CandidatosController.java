package com.lifters.voter.registration.controllers;

import com.lifters.voter.registration.models.dtos.candidatos.CandidatosRequestAtualizarDto;
import com.lifters.voter.registration.models.dtos.candidatos.CandidatosRequestDto;
import com.lifters.voter.registration.models.dtos.candidatos.CandidatosResponseDto;
import com.lifters.voter.registration.models.dtos.candidatos.RelatorioDto;
import com.lifters.voter.registration.services.impl.CandidatosServiceImpl;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin(origins ="*")
@RequestMapping("/api/candidatos")
@AllArgsConstructor
public class CandidatosController {

    private final CandidatosServiceImpl candidatosService;

    @PostMapping
    public ResponseEntity<CandidatosResponseDto> criarCandidatos(@RequestBody @Valid CandidatosRequestDto candidatosRequestDto){
        return ResponseEntity.status(HttpStatus.OK).body(candidatosService.criarCandidatos(candidatosRequestDto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CandidatosResponseDto> buscarCandidatoPorId(@PathVariable UUID id) {
        return ResponseEntity.status(HttpStatus.OK).body(candidatosService.buscarCandidatoPorId(id));
    }

    @GetMapping
    public ResponseEntity<List<CandidatosResponseDto>> buscarCandidatos(){
        return ResponseEntity.status(HttpStatus.OK).body(candidatosService.buscarCandidatos());
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> deletarCandidato(@PathVariable UUID id) {
        candidatosService.deletarCandidato(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<CandidatosResponseDto> alterarCandidatos(@PathVariable UUID id, @RequestBody CandidatosRequestAtualizarDto candidatosRequestAtualizarDto){
        return ResponseEntity.status(HttpStatus.OK).body(candidatosService.alterarCandidato(id, candidatosRequestAtualizarDto));
    }

    @GetMapping("/relatorio")
    public ResponseEntity<List<RelatorioDto>> relatorio () {
        return ResponseEntity.status(HttpStatus.OK).body(candidatosService.relatorio());
    }
}
