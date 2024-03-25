package com.lifters.voter.registration.repositories;

import com.lifters.voter.registration.models.EleitorModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface EleitoresRepository extends JpaRepository<EleitorModel, UUID> {

    boolean existsByCpf(String cpf);
}
