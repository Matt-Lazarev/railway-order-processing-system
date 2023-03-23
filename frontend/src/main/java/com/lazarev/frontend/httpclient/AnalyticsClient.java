package com.lazarev.frontend.httpclient;

import com.lazarev.model.analytics.FlightParametersCalcRequest;
import com.lazarev.model.analytics.FlightParametersCalcResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "analytics-client",
             url = "http://localhost:8081/api/calculator")
public interface AnalyticsClient {

    @PostMapping
    FlightParametersCalcResponse calculateFlightParameters(@RequestBody FlightParametersCalcRequest request);
}
