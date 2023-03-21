package com.lazarev.analyticsservice.httpclient;

import com.lazarev.model.analytics.RjdCalculatorRequest;
import com.lazarev.model.analytics.RjdCalculatorResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@FeignClient(name = "personal-account-client",
             url = "https://rjd/api/services")
public interface RjdClient {

    @PostMapping("/rate/calculator")
    RjdCalculatorResponse calculateFlightRate(@RequestBody RjdCalculatorRequest request);
}
