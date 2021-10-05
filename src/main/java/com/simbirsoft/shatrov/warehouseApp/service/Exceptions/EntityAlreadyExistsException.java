package com.simbirsoft.shatrov.warehouseApp.service.Exceptions;

public class EntityAlreadyExistsException extends Exception {

    public EntityAlreadyExistsException() {
    }

    public EntityAlreadyExistsException(String message) {
        super(message);
    }
}
