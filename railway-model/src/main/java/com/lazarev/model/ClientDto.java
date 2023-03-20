package com.lazarev.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ClientDto {
    private Integer id;
    private String firstname;
    private String lastname;
    private String username;
    private String organization;
    private String email;
    private Integer userId;
    private Integer managerId;
}
