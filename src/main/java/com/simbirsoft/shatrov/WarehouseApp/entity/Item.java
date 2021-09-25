package com.simbirsoft.shatrov.WarehouseApp.entity;

import javax.persistence.*;

import lombok.Data;

@Data
@Table(name = "item")
@Entity

public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "i_id", nullable = false)
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "amount", nullable = false)
    private Integer amount;

    @Column(name = "price", nullable = false)
    private Integer price;

    @ManyToOne(optional = false)
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

}