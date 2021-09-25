package com.simbirsoft.shatrov.WarehouseApp.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@Entity
@Table(name = "user", schema = "public")
public class User {

    @Id
    @NotNull
    @NotEmpty
    @Column(name = "login", nullable = false)
    private String login;

    @NotNull
    @NotEmpty
    @Column(name = "password", nullable = false)
    private String password;

    @NotNull
    @NotEmpty
    @Column(name = "role", nullable = false)
    private String role;
}