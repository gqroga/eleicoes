package com.lifters.voter.registration.repositories;

import com.lifters.voter.registration.models.CandidatoModel;
import com.lifters.voter.registration.models.dtos.candidatos.RelatorioDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CandidatosRepository extends JpaRepository<CandidatoModel, UUID> {

    boolean existsByNome(String nome);

    @Query("SELECT " +
            "candidatoModel.id, " +
            "candidatoModel.nome, " +
            "COUNT(votoModel.id), " +
            "candidatoModel.cargo " +
            "FROM " +
            "VotoModel votoModel " +
            "JOIN " +
            "votoModel.candidatoModel candidatoModel " +
            "GROUP BY " +
            "candidatoModel.nome, " +
            "candidatoModel.id, " +
            "candidatoModel.cargo " +
            "HAVING " +
            "COUNT(votoModel.id) = (" +
            "SELECT " +
            "COUNT(vm.id) " +
            "FROM " +
            "VotoModel vm " +
            "WHERE " +
            "vm.candidatoModel = candidatoModel " +
            "GROUP BY " +
            "vm.candidatoModel " +
            "ORDER BY " +
            "COUNT(vm.id) DESC " +
            "LIMIT 1" +
            ")"
    )
    List<Object[]> findCandidatosComMaiorVotoPorCargo();
}
