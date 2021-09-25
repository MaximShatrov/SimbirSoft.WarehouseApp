package com.simbirsoft.shatrov.WarehouseApp.controller;

import com.simbirsoft.shatrov.WarehouseApp.Exceptions.*;
import com.simbirsoft.shatrov.WarehouseApp.entity.Category;

import com.simbirsoft.shatrov.WarehouseApp.service.category.CategoryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/api/v1/categories")
public class CategoryController {

    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @RequestMapping(value = "", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Category> createItem(@RequestBody @Valid Category category) {
        HttpHeaders headers = new HttpHeaders();
        try {
            categoryService.createCategory(category);
            return new ResponseEntity<>(category, headers, HttpStatus.CREATED);
        } catch (NullEntityException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Category> readCategory(@PathVariable("id") Integer id) {
        try {
            return new ResponseEntity<>(categoryService.readCategory(id), HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Category> updateItem(@RequestBody @Valid Category category, UriComponentsBuilder builder) {
        HttpHeaders headers = new HttpHeaders();

        try {
            categoryService.updateCategory(category);
            return new ResponseEntity<>(category, headers, HttpStatus.OK);
        } catch (NullEntityException | EntityNotFoundException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Category> deleteUser(@PathVariable("id") Integer id) {
        try {
            categoryService.deleteCategory(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (EntityNotFoundException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Category>> getAllUsers() {
        try {
            return new ResponseEntity<>(categoryService.findAll(), HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
