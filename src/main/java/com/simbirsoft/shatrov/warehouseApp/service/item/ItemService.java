package com.simbirsoft.shatrov.warehouseApp.service.item;

import com.simbirsoft.shatrov.warehouseApp.service.Exceptions.*;
import com.simbirsoft.shatrov.warehouseApp.entity.Item;

import java.util.List;

public interface ItemService {
    //create
    void createItem(Item item) throws NullEntityException;

    //read
    Item readItem(Integer id) throws EntityNotFoundException;

    //Update
    void updateItem(Item item) throws EntityNotFoundException, NullEntityException;

    //delete
    void deleteItem(Integer id) throws EntityNotFoundException;

    //getAll
    List<Item> findAll() throws EntityNotFoundException;
}
