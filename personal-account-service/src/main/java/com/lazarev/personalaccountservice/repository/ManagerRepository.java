package com.lazarev.personalaccountservice.repository;

import com.lazarev.personalaccountservice.entity.Manager;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ManagerRepository extends JpaRepository<Manager, Integer> {
}
