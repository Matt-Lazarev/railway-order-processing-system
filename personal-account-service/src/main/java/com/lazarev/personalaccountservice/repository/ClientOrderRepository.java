package com.lazarev.personalaccountservice.repository;

import com.lazarev.personalaccountservice.entity.ClientOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ClientOrderRepository extends JpaRepository<ClientOrder, Integer> {

    @Query("""
              select co from ClientOrder co
              where co.id = :clientOrderId and co.client.id = :clientId
           """)
    Optional<ClientOrder> findClientOrderByClientIdAndClientOrderId(Integer clientId, Integer clientOrderId);
}
