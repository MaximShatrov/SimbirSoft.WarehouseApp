package com.simbirsoft.shatrov.WarehouseApp.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "user", schema = "public")
public class User {

    @Id
    @Column(name = "login", nullable = false)
    private String login;


    @Column(name = "password", nullable = false)
    private String password;


    @Column(name = "role", nullable = false)
    private String role;
}