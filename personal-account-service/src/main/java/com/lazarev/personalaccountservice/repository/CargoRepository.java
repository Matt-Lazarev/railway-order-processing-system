package com.lazarev.personalaccountservice.repository;

import com.lazarev.personalaccountservice.entity.Cargo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface CargoRepository extends JpaRepository<Cargo, Integer> {

    @Query("select c from Cargo c where c.name = :cargoName")
    Optional<Cargo> findCargoByName(String cargoName);
}
