package com.lazarev.personalaccountservice.repository;

import com.lazarev.personalaccountservice.entity.Station;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface StationRepository extends JpaRepository<Station, Integer> {

    @Query("select s from Station s where s.name = :stationName")
    Optional<Station> findStationByName(String stationName);
}
