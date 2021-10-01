package com.simbirsoft.shatrov.WarehouseApp.controller;

import com.simbirsoft.shatrov.WarehouseApp.service.Exceptions.*;
import com.simbirsoft.shatrov.WarehouseApp.entity.Item;
import com.simbirsoft.shatrov.WarehouseApp.service.item.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;


@RestController
@RequestMapping("/api/v1/items")
public class ItemController {

    private final ItemService itemService;

    @Autowired
    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

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

    @PreAuthorize("hasAuthority('items:read')")
    @RequestMapping(value = "{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> readItem(@PathVariable("id") Integer id) {
        try {
            return new ResponseEntity<>(itemService.readItem(id), HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>("Item with id:" + id + " not found.", HttpStatus.NOT_FOUND);
        }
    }

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

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> deleteItem(@PathVariable("id") Integer id) {
        try {
            itemService.deleteItem(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>("Item with id:" + id + " not found.", HttpStatus.NOT_FOUND);
        }
    }

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
