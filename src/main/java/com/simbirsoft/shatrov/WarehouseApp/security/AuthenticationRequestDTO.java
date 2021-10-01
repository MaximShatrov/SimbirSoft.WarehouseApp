package com.simbirsoft.shatrov.WarehouseApp.security;

import lombok.Data;

@Data
public class AuthenticationRequestDTO {
    private String login;
    private String password;
}