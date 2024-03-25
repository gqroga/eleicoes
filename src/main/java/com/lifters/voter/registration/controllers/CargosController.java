package com.lifters.voter.registration.controllers;

import com.lifters.voter.registration.models.dtos.cargos.CargosRequestAtualizarDto;
import com.lifters.voter.registration.models.dtos.cargos.CargosRequestDto;
import com.lifters.voter.registration.models.dtos.cargos.CargosResponseDto;
import com.lifters.voter.registration.services.impl.CargosServiceImpl;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("/api/cargos")
public class CargosController {

    private final CargosServiceImpl cargosService;

    @PostMapping()
    public ResponseEntity<CargosResponseDto> salvarCargo(@RequestBody @Valid CargosRequestDto cargosRequestDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(cargosService.criarCargo(cargosRequestDto));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable UUID id) {
        cargosService.deletar(id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CargosResponseDto> buscarPorId(@PathVariable UUID id) {
        return ResponseEntity.status(HttpStatus.OK).body(cargosService.buscarPorId(id));
    }

    @GetMapping()
    public ResponseEntity<List<CargosResponseDto>> buscarCargos() {
        return ResponseEntity.status(HttpStatus.OK).body(cargosService.buscarCargos());
    }

    @PutMapping("/{id}")
    public ResponseEntity<CargosResponseDto> alterarCargo(@PathVariable UUID id, @RequestBody CargosRequestAtualizarDto cargosRequestAtualizarDto) {
        return ResponseEntity.status(HttpStatus.OK).body(cargosService.alterarCargo(id, cargosRequestAtualizarDto));
    }
}
