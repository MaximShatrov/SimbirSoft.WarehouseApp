package com.simbirsoft.shatrov.WarehouseApp.service.Exceptions;

public class EntityAlreadyExistsException extends Exception {

    public EntityAlreadyExistsException() {
    }

    public EntityAlreadyExistsException(String message) {
        super(message);
    }
}
