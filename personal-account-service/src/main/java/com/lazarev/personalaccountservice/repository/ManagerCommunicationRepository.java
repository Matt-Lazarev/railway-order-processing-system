package com.lazarev.personalaccountservice.repository;

import com.lazarev.personalaccountservice.entity.ManagerCommunication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ManagerCommunicationRepository extends JpaRepository<ManagerCommunication, Integer> {
    @Query("""
              select mc
              from ManagerCommunication mc
              left join fetch mc.clientOrder
              where mc.client.id = :clientId
           """)
    List<ManagerCommunication> findAllManagerCommunicationsByClientId(Integer clientId);
}
