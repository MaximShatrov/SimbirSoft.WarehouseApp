package com.simbirsoft.shatrov.WarehouseApp.controller;

import com.simbirsoft.shatrov.WarehouseApp.Exceptions.*;
import com.simbirsoft.shatrov.WarehouseApp.entity.Item;
import com.simbirsoft.shatrov.WarehouseApp.service.item.ItemService;
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
@RequestMapping("/api/v1/items")
public class ItemController {

    private final ItemService itemService;

    @Autowired
    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @RequestMapping(value = "", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Item> createItem(@RequestBody @Valid Item item) {
        HttpHeaders headers = new HttpHeaders();
        try {
            itemService.createItem(item);
            return new ResponseEntity<>(item, headers, HttpStatus.CREATED);
        } catch (NullEntityException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Item> readItem(@PathVariable("id") Integer id) {
        try {
            return new ResponseEntity<>(itemService.readItem(id), HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Item> updateItem(@RequestBody @Valid Item item, UriComponentsBuilder builder) {
        HttpHeaders headers = new HttpHeaders();

        try {
            itemService.updateItem(item);
            return new ResponseEntity<>(item, headers, HttpStatus.OK);
        } catch (NullEntityException | EntityNotFoundException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Item> deleteItem(@PathVariable("id") Integer id) {
        try {
            itemService.deleteItem(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (EntityNotFoundException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

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
