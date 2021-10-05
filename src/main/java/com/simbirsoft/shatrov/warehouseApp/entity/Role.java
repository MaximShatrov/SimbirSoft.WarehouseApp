package com.simbirsoft.shatrov.warehouseApp.entity;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum Role {
    ADMIN(Stream.of(Permission.USERS_READ, Permission.USERS_WRITE,
            Permission.ITEMS_READ, Permission.ITEMS_WRITE,
            Permission.CATEGORIES_READ, Permission.CATEGORIES_WRITE).collect(Collectors.toSet())),
    USER(Stream.of(Permission.ITEMS_READ,
            Permission.CATEGORIES_READ).collect(Collectors.toSet())),
    EMPLOYEE(Stream.of(Permission.ITEMS_READ, Permission.ITEMS_WRITE,
            Permission.CATEGORIES_READ, Permission.CATEGORIES_WRITE).collect(Collectors.toSet()));

    private final Set<Permission> permissions;

    Role(Set<Permission> permissions) {
        this.permissions = permissions;
    }

    public Set<Permission> getPermissions() {
        return permissions;
    }

    public Set<SimpleGrantedAuthority> getAuthorities() {
        return getPermissions().stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toSet());
    }
}
