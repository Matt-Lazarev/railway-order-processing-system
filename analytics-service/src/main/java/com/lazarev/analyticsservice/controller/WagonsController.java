package com.lazarev.analyticsservice.controller;

import com.lazarev.analyticsservice.entity.Wagon;
import com.lazarev.analyticsservice.service.WagonService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/wagons")
public class WagonsController {
    private final WagonService wagonService;

    @GetMapping
    @CrossOrigin("*")
    public List<Wagon> getAllWagons(@RequestParam Integer clientOrderId){
        return wagonService.getAllWagonsByClientOrderId(clientOrderId);
    }
}
