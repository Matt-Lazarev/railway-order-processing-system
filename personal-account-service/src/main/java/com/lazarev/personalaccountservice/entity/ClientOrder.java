package com.lazarev.personalaccountservice.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@Entity
@Table(name="client_orders")
public class ClientOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private LocalDate beginDate;

    private LocalDate endDate;

    private String status;

    private Integer wagonAmount;

    private Integer wagonVolume;

    private Integer totalVolume;

    private BigDecimal rate;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id", referencedColumnName = "id")
    private Client client;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "manager_id", referencedColumnName = "id")
    private Manager manager;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "cargo_id", referencedColumnName = "id")
    private Cargo cargo;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "source_station_id", referencedColumnName = "id")
    private Station sourceStation;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "dest_station_id", referencedColumnName = "id")
    private Station destStation;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "clientOrder", orphanRemoval = true)
    private List<Document> documents = new ArrayList<>();

    public void setDocuments(List<Document> documents){
        documents.forEach(document -> document.setClientOrder(this));
        this.documents = documents;
    }

    public void addDocument(Document document){
        document.setClientOrder(this);
        this.documents.add(document);
    }
}
