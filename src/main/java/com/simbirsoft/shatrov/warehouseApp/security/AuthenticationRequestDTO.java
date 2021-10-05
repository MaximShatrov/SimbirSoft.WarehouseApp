package com.simbirsoft.shatrov.warehouseApp.security;

import lombok.Data;

@Data
public class AuthenticationRequestDTO {
    private String login;
    private String password;
}