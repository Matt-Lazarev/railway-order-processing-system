package com.lazarev.personalaccountservice.repository;

import com.lazarev.model.ClientOrderDto;
import com.lazarev.personalaccountservice.entity.Client;
import com.lazarev.personalaccountservice.entity.Manager;
import com.lazarev.personalaccountservice.entity.ManagerCommunication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client, Integer> {
    @Query("select c from Client c where c.username = :username")
    Optional<Client> findClientByUsername(String username);

    @Query("""
              select new com.lazarev.model.ClientOrderDto(co.id, co.status, co.sourceStation.name, co.destStation.name, c.id)
              from Client c
              left join c.clientOrders co
              where c.id = :clientId
           """)
    List<ClientOrderDto> findAllClientOrdersByClientId(Integer clientId);

    @Query("""
              select co.id
              from Client c
              left join c.clientOrders co
              where c.id = :clientId
           """)
    List<Integer> findClientOrderIdsByClientId(Integer clientId);

    @Query("""
              select c.manager
              from Client c
              where c.id = :clientId
           """)
    Optional<Manager> findManagerByClientId(Integer clientId);

}
