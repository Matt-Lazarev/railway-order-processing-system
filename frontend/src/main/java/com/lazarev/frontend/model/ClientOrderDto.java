package com.lazarev.frontend.model;

public record ClientOrderDto (
        Integer id,
        String status,
        String sourceStation,
        String destStation,
        Integer clientId
) { }
