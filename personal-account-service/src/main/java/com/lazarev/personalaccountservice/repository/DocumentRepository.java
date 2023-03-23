package com.lazarev.personalaccountservice.repository;

import com.lazarev.personalaccountservice.entity.Document;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface DocumentRepository extends JpaRepository<Document, Integer> {
    @Query("""
              select d
              from Document d
              inner join d.clientOrder co
              where co.client.id = :clientId
           """)
    List<Document> findAllDocumentsByClientId(Integer clientId);

    @Query("""
              select d
              from Document d
              inner join fetch d.clientOrder co
              where d.id = :documentId
           """)
    Optional<Document> findDocumentById(Integer documentId);
}
