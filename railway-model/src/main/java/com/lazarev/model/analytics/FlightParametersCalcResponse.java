package com.lazarev.model.analytics;

import java.math.BigDecimal;
import java.time.LocalDate;

public record FlightParametersCalcResponse  (
        LocalDate orderEnd,
        Integer wagonAmount,
        Integer wagonVolume,
        BigDecimal rate) { }
