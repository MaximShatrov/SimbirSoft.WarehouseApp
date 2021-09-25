package com.simbirsoft.shatrov.WarehouseApp.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Table(name = "category", indexes = {
        @Index(name = "category_name_uindex", columnList = "name", unique = true)
})
@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "c_id", nullable = false)
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;
}