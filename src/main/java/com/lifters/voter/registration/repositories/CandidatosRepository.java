package com.lifters.voter.registration.repositories;

import com.lifters.voter.registration.models.CandidatoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CandidatosRepository extends JpaRepository<CandidatoModel, UUID> {

    boolean existsByNome(String nome);

    @Query("""
        SELECT
                idCargo, cargo, MAX(totalVotos), idCandidatoVencedor, nomeCandidatoVencedor
        FROM
        (
                SELECT cargoModel.id AS idCargo, cargoModel.nome AS cargo, COUNT(votoModel.id) AS totalVotos, candidatoModel.id AS idCandidatoVencedor, candidatoModel.nome AS nomeCandidatoVencedor
                FROM
                        VotoModel votoModel
                JOIN
                        votoModel.candidatoModel candidatoModel
                JOIN
                        candidatoModel.cargoModel cargoModel
                GROUP BY
                        candidatoModel.nome,
                        candidatoModel.id,
                        cargoModel.nome,
                        cargoModel.id
                HAVING
                        COUNT(votoModel.id) = (
                                SELECT
                                        COUNT(vm.id)
                                FROM
                                        VotoModel vm
                                WHERE
                                        vm.candidatoModel = candidatoModel
                                GROUP BY
                                        vm.candidatoModel
                                ORDER BY
                                        COUNT(vm.id) DESC
                                LIMIT 1
                        )
        )
        GROUP BY
                idCargo,
                cargo,
                idCandidatoVencedor,
                nomeCandidatoVencedor
"""
    )
    List<Object[]> findCandidatosComMaiorVotoPorCargo();
}

