package com.lazarev.model;

public record ManagerDto (Integer managerId,
                          Integer clientId,
                          String firstname,
                          String lastname,
                          String phone,
                          String email) { }
