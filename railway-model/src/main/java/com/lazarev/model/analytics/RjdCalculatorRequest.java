package com.lazarev.model.analytics;

public record RjdCalculatorRequest(String sourceStation,
                                   String destStation,
                                   String cargo,
                                   Integer totalVolume) { }
