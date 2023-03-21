package com.lazarev.analyticsservice.repository;

import com.lazarev.analyticsservice.entity.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FlightRepository extends JpaRepository<Flight, Integer> {
    @Query("""
           select f from Flight f
           where f.sourceStation = :sourceStation and
                 f.destStation = :destStation and
                 f.cargo = :cargo
           """)
    List<Flight> findAllFlightByStationsAndCargo(String sourceStation, String destStation, String cargo);
}
