package com.simbirsoft.shatrov.warehouseApp.service.category;

import com.simbirsoft.shatrov.warehouseApp.service.Exceptions.EntityNotFoundException;
import com.simbirsoft.shatrov.warehouseApp.service.Exceptions.NullEntityException;
import com.simbirsoft.shatrov.warehouseApp.entity.Category;

import java.util.List;

public interface CategoryService {
    //create
    void createCategory(Category category) throws NullEntityException;

    //read
    Category readCategory(Integer id) throws EntityNotFoundException;

    //Update
    void updateCategory(Category category) throws EntityNotFoundException, NullEntityException;

    //delete
    void deleteCategory(Integer id) throws EntityNotFoundException;

    //getAll
    List<Category> findAll() throws EntityNotFoundException;
}
