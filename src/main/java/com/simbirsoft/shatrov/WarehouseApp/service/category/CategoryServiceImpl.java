package com.simbirsoft.shatrov.WarehouseApp.service.category;

import com.simbirsoft.shatrov.WarehouseApp.Exceptions.EntityNotFoundException;
import com.simbirsoft.shatrov.WarehouseApp.Exceptions.NullEntityException;
import com.simbirsoft.shatrov.WarehouseApp.entity.Category;
import com.simbirsoft.shatrov.WarehouseApp.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void createCategory(Category category) throws NullEntityException {
        if (category.getName() == null || category.getName().isEmpty() || category == null) {
            throw new NullEntityException("Category name is empty or NULL.");
        }
        categoryRepository.save(category);
    }

    @Override
    public Category readCategory(Integer id) throws EntityNotFoundException {
        if (categoryRepository.existsById(id)) {
            return categoryRepository.findById(id).get();
        }
        throw new EntityNotFoundException("Category with id:" + id + " not found.");
    }

    @Override
    public void updateCategory(Category category) throws EntityNotFoundException, NullEntityException {
        if (category.getName() == null || category.getName().isEmpty() || category == null) {
            throw new NullEntityException("Category name is empty or NULL.");
        }
        if (categoryRepository.existsById(category.getId())) {
            categoryRepository.save(category);
            return;
        }
        throw new EntityNotFoundException("Category with id:" + category.getId() + " not found.");
    }

    @Override
    public void deleteCategory(Integer id) throws EntityNotFoundException {
        if (categoryRepository.existsById(id)) {
            categoryRepository.deleteById(id);
            return;
        }
        throw new EntityNotFoundException("Category with id:" + id + " not found.");
    }

    @Override
    public List<Category> findAll() throws EntityNotFoundException {
        if (categoryRepository.findAll().isEmpty()) {
            throw new EntityNotFoundException("DB is empty.");
        }
        return categoryRepository.findAll();
    }
}
