package com.simbirsoft.shatrov.WarehouseApp.controller;

import com.simbirsoft.shatrov.WarehouseApp.service.Exceptions.*;
import com.simbirsoft.shatrov.WarehouseApp.entity.Category;

import com.simbirsoft.shatrov.WarehouseApp.service.category.CategoryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1/categories")
public class CategoryController {

    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PreAuthorize("hasAuthority('categories:write')")
    @RequestMapping(value = "", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createCategory(@RequestBody Category category) {
        try {
            categoryService.createCategory(category);
            return new ResponseEntity<>(category, HttpStatus.CREATED);
        } catch (NullEntityException e) {
            return new ResponseEntity<>("Incorrect category description.", HttpStatus.BAD_REQUEST);
        }
    }

    @PreAuthorize("hasAuthority('categories:read')")
    @RequestMapping(value = "{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> readCategory(@PathVariable("id") Integer id) {
        try {
            return new ResponseEntity<>(categoryService.readCategory(id), HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>("Incorrect category id.", HttpStatus.NOT_FOUND);
        }
    }

    @PreAuthorize("hasAuthority('categories:write')")
    @RequestMapping(value = "", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateCategory(@RequestBody Category category) {
        try {
            categoryService.updateCategory(category);
            return new ResponseEntity<>(category, HttpStatus.OK);
        } catch (NullEntityException | EntityNotFoundException e) {
            return new ResponseEntity<>("Category not found or incorrect.", HttpStatus.BAD_REQUEST);
        }
    }

    @PreAuthorize("hasAuthority('categories:write')")
    @RequestMapping(value = "{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> deleteCategory(@PathVariable("id") Integer id) {
        try {
            categoryService.deleteCategory(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>("Category with id:" + id + " not found.", HttpStatus.NOT_FOUND);
        }
    }

    @PreAuthorize("hasAuthority('categories:read')")
    @RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Category>> getAllCategories() {
        try {
            return new ResponseEntity<>(categoryService.findAll(), HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
