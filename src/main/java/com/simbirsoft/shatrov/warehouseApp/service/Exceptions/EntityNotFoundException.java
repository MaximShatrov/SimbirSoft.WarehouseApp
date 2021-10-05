package com.simbirsoft.shatrov.warehouseApp.service.Exceptions;

public class EntityNotFoundException extends Exception {
    public EntityNotFoundException() {
    }

    public EntityNotFoundException(String message) {
        super(message);
    }
}
