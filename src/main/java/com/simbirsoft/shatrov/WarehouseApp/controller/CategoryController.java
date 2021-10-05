package com.simbirsoft.shatrov.WarehouseApp.controller;

import com.simbirsoft.shatrov.WarehouseApp.service.Exceptions.*;
import com.simbirsoft.shatrov.WarehouseApp.entity.Category;
import com.simbirsoft.shatrov.WarehouseApp.service.category.CategoryService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@Tag(name = "Categories")
@RequestMapping("/api/v1/categories")
public class CategoryController {

    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @Operation(summary = "Create new item's category")
    @ApiResponse(description = "Category create success.", responseCode = "201",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Category.class)))
    @ApiResponse(description = "Category create failed, wrong request.", responseCode = "400", content = @Content)
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

    @Operation(summary = "Get category's info")
    @ApiResponse(description = "Get category's info success.", responseCode = "200",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Category.class)))
    @ApiResponse(description = "Category not found.", responseCode = "404", content = @Content)
    @PreAuthorize("hasAuthority('categories:read')")
    @RequestMapping(value = "{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> readCategory(@PathVariable("id") Integer id) {
        try {
            return new ResponseEntity<>(categoryService.readCategory(id), HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>("Incorrect category id.", HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Edit category's info")
    @ApiResponse(description = "Edit category's info success.", responseCode = "200",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Category.class)))
    @ApiResponse(description = "Edit category's info failed. Category not found or wrong request.", responseCode = "400", content = @Content)
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

    @Operation(summary = "Delete category")
    @ApiResponse(description = "Delete category success.", responseCode = "204", content = @Content)
    @ApiResponse(description = "Category delete failed. Category not found.", responseCode = "404", content = @Content)
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

    @Operation(summary = "Get list of catgegories")
    @ApiResponse(description = "Get list of catgegories success.", responseCode = "200",
            content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Category.class))))
    @ApiResponse(description = "Categories not found.", responseCode = "404", content = @Content)
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
