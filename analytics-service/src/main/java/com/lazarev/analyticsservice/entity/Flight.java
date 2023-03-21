package com.lazarev.analyticsservice.entity;

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
@Table(name="flights")
public class Flight {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String cargo;

    private String sourceStation;

    private String destStation;

    private LocalDate beginDate;

    private LocalDate endDate;

    private Integer totalVolume;

    private Integer wagonAmount;

    private Integer wagonVolume;

    private BigDecimal rate;

    private Integer clientOrderId;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "flight")
    private List<Wagon> wagons = new ArrayList<>();

    public void setWagons(List<Wagon> wagons){
        wagons.forEach(wagon -> wagon.setFlight(this));
        this.wagons = wagons;
    }

    public void addWagon(Wagon wagon){
        wagon.setFlight(this);
        this.wagons.add(wagon);
    }
}
