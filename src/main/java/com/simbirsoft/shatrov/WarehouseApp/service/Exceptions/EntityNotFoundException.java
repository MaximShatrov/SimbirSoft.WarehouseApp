package com.simbirsoft.shatrov.WarehouseApp.service.Exceptions;

public class EntityNotFoundException extends Exception {
    public EntityNotFoundException() {
    }

    public EntityNotFoundException(String message) {
        super(message);
    }
}
