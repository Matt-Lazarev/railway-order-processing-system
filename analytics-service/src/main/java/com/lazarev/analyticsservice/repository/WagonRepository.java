package com.lazarev.analyticsservice.repository;

import com.lazarev.analyticsservice.entity.Wagon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface WagonRepository extends JpaRepository<Wagon, Integer> {
    @Query("select w from Wagon w where w.status = 'Свободен'")
    List<Wagon> findAllFreeWagons();

    @Query("""
            select w from Wagon w
            inner join w.flight f
            where f.clientOrderId = :clientOrderId
           """)
    List<Wagon> findAllWagonsByClientOrderId(Integer clientOrderId);

    @Modifying
    @Query("update Wagon w set w.status = :newStatus where w.id in :ids")
    void updateWagonStatus(List<Integer> ids, String newStatus);
}
