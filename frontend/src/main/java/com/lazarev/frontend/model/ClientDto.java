package com.lazarev.frontend.model;

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
    private String organization;
    private String email;
    private Integer userId;
    private Integer managerId;
}
