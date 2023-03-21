package com.lazarev.model.analytics;

import java.time.LocalDate;

public record FlightParametersCalcRequest(
        LocalDate orderBeginDate,
        String sourceStation,
        String destStation,
        String cargo,
        Integer totalVolume,
        Integer clientOrderId) { }
