package com.simbirsoft.shatrov.WarehouseApp.service.user;

import com.simbirsoft.shatrov.WarehouseApp.service.Exceptions.*;
import com.simbirsoft.shatrov.WarehouseApp.entity.User;

import java.util.List;

public interface UserService {
    //create
    void createUser(User user) throws NullEntityException, EntityAlreadyExistsException;

    //read
    User readUser(String login) throws EntityNotFoundException;

    //Update
    void updateUser(User user) throws EntityNotFoundException, NullEntityException;

    //delete
    void deleteUser(String login) throws EntityNotFoundException;

    //getAll
    List<User> findAll() throws EntityNotFoundException;
}
