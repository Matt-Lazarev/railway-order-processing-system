package com.lazarev.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClientOrderCardDto {
    private Integer clientOrderId;
    private Integer clientId;
    private String clientFullName;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate orderBegin;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate orderEnd;

    private String status;
    private String cargo;
    private String sourceStation;
    private String destStation;
    private Integer totalVolume;
    private Integer wagonAmount;
    private Integer wagonVolume;
    private BigDecimal rate;
}
