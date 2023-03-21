package com.lazarev.analyticsservice.controller;

import com.lazarev.analyticsservice.service.CalculationService;
import com.lazarev.model.analytics.FlightParametersCalcRequest;
import com.lazarev.model.analytics.FlightParametersCalcResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/calculator")
public class CalculationController {
    private final CalculationService calculationService;

    @PostMapping
    public FlightParametersCalcResponse calculateFlightParameters(@RequestBody FlightParametersCalcRequest request){
        return calculationService.calculateFlightParameters(request);
    }
}
