package com.simbirsoft.shatrov.warehouseApp.entity;

public enum Permission {
    USERS_READ("users:read"),
    USERS_WRITE("users:write"),
    ITEMS_READ("items:read"),
    ITEMS_WRITE("items:write"),
    CATEGORIES_READ("categories:read"),
    CATEGORIES_WRITE("categories:write");


    private final String permission;

    Permission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}