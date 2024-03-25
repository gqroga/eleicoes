package com.lifters.voter.registration.repositories;

import com.lifters.voter.registration.models.VotoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface VotoRepository extends JpaRepository<VotoModel, UUID> {

    VotoModel findVotoByEleitorModelId(UUID idEleitor);
    VotoModel findVotoByCandidatoModelId(UUID idCandidato);

}
