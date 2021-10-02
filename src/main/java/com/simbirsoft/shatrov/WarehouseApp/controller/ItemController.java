package com.simbirsoft.shatrov.WarehouseApp.controller;

import com.simbirsoft.shatrov.WarehouseApp.service.Exceptions.*;
import com.simbirsoft.shatrov.WarehouseApp.entity.Item;
import com.simbirsoft.shatrov.WarehouseApp.service.item.ItemService;

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
@Tag(name = "Items")
@RequestMapping("/api/v1/items")
public class ItemController {

    private final ItemService itemService;

    @Autowired
    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @Operation(summary = "Create new item")
    @ApiResponse(description = "Item create success.", responseCode = "201",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Item.class)))
    @ApiResponse(description = "Item create failed, wrong request.", responseCode = "400", content = @Content)
    @PreAuthorize("hasAuthority('items:write')")
    @RequestMapping(value = "", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createItem(@RequestBody Item item) {
        try {
            itemService.createItem(item);
            return new ResponseEntity<>(item, HttpStatus.CREATED);
        } catch (NullEntityException e) {
            return new ResponseEntity<>("Incorrect item description.", HttpStatus.BAD_REQUEST);
        }
    }

    @Operation(summary = "Get item's info")
    @ApiResponse(description = "Get item's info success.", responseCode = "200",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Item.class)))
    @ApiResponse(description = "Item not found.", responseCode = "404", content = @Content)
    @PreAuthorize("hasAuthority('items:read')")
    @RequestMapping(value = "{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> readItem(@PathVariable("id") Integer id) {
        try {
            return new ResponseEntity<>(itemService.readItem(id), HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>("Item with id:" + id + " not found.", HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Edit item's info")
    @ApiResponse(description = "Edit item's info success.", responseCode = "200",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Item.class)))
    @ApiResponse(description = "Edit item's info failed. Item not found or wrong request.", responseCode = "400", content = @Content)
    @PreAuthorize("hasAuthority('items:write')")
    @RequestMapping(value = "", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateItem(@RequestBody Item item) {
        try {
            itemService.updateItem(item);
            return new ResponseEntity<>(item, HttpStatus.OK);
        } catch (NullEntityException | EntityNotFoundException e) {
            return new ResponseEntity<>("Item not found or incorrect.", HttpStatus.BAD_REQUEST);
        }
    }

    @Operation(summary = "Delete item")
    @ApiResponse(description = "Delete item success.", responseCode = "204", content = @Content)
    @ApiResponse(description = "Item delete failed. Item not found.", responseCode = "404", content = @Content)
    @RequestMapping(value = "{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> deleteItem(@PathVariable("id") Integer id) {
        try {
            itemService.deleteItem(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>("Item with id:" + id + " not found.", HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Get list of items")
    @ApiResponse(description = "Get list of items success.", responseCode = "200",
            content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Item.class))))
    @ApiResponse(description = "Items not found.", responseCode = "404", content = @Content)
    @PreAuthorize("hasAuthority('items:read')")
    @RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Item>> getAllItems() {
        try {
            return new ResponseEntity<>(itemService.findAll(), HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
