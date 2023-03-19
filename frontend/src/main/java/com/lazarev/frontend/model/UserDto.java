package com.lazarev.frontend.model;

public record UserDto(
        Integer id,
        String login,
        String password,
        String role
) { }
