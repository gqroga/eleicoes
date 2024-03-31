package com.lifters.voter.registration.controllers;

import com.lifters.voter.registration.models.dtos.eleitores.EleitoresRequestAtualizarDto;
import com.lifters.voter.registration.models.dtos.eleitores.EleitoresRequestDto;
import com.lifters.voter.registration.models.dtos.eleitores.EleitoresResponseDto;
import com.lifters.voter.registration.models.dtos.eleitores.VotarDTO;
import com.lifters.voter.registration.services.impl.EleitoresServiceImpl;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin(origins ="*")
@RequestMapping(value = "/api/eleitores")
@AllArgsConstructor
public class EleitoresController {

    private final EleitoresServiceImpl eleitoresService;

    @PostMapping
    public ResponseEntity<EleitoresResponseDto> criarEleitores(@RequestBody @Valid EleitoresRequestDto eleitoresRequestDto){
        return ResponseEntity.status(HttpStatus.OK).body(eleitoresService.criarEleitores(eleitoresRequestDto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<EleitoresResponseDto> buscarEleitorPorId(@PathVariable UUID id){
        return ResponseEntity.status(HttpStatus.OK).body(eleitoresService.buscarEleitorPorId(id));
    }

    @GetMapping
    public ResponseEntity<List<EleitoresResponseDto>> bucarEleitores(){
        return ResponseEntity.status(HttpStatus.OK).body(eleitoresService.buscarEleitores());
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> deletarEleitor(@PathVariable UUID id){
        eleitoresService.deletarEleitor(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<EleitoresResponseDto> alterarEleitores(@PathVariable UUID id, @RequestBody EleitoresRequestAtualizarDto eleitoresRequestAtualizarDto) {
        return ResponseEntity.status(HttpStatus.OK).body(eleitoresService.alterarEleitor(id, eleitoresRequestAtualizarDto));
    }

    @PostMapping("/{id}/votar")
    public ResponseEntity<Void> votar (@PathVariable UUID id, @Valid @RequestBody VotarDTO votarDTO) {
        eleitoresService.votar(id, votarDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
