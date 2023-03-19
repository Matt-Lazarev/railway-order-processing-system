package com.lazarev.frontend.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClientOrderCardDto {
    private Integer clientOrderId;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate orderBegin;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate orderEnd;

    private String status;
    private String cargo;
    private String sourceStation;
    private String destStation;
    private Integer totalVolume;
    private Integer clientId;
}
