package com.lifters.voter.registration.repositories;

import com.lifters.voter.registration.models.CargoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface CargosRepository extends JpaRepository<CargoModel, UUID> {

}
