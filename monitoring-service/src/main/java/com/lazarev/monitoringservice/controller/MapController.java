package com.lazarev.monitoringservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
@RequestMapping("/railway-system/map")
public class MapController {

    @GetMapping
    public String getMap(@RequestParam Integer clientOrderId, Model model){
        model.addAttribute("clientOrderId", clientOrderId);
        return "map";
    }
}
