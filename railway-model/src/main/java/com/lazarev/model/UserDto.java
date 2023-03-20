package com.lazarev.model;

public record UserDto(
        Integer id,
        String login,
        String password,
        String role
) { }
