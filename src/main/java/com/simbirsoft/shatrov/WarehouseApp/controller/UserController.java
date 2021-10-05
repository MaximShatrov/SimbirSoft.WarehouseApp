package com.simbirsoft.shatrov.WarehouseApp.controller;

import com.simbirsoft.shatrov.WarehouseApp.service.Exceptions.*;
import com.simbirsoft.shatrov.WarehouseApp.entity.User;
import com.simbirsoft.shatrov.WarehouseApp.service.user.UserService;

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
@Tag(name = "Users")
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Operation(summary = "Create new user")
    @ApiResponse(description = "User create success.", responseCode = "201",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = User.class)))
    @ApiResponse(description = "User create failed. Wrong request.", responseCode = "400", content = @Content)
    @ApiResponse(description = "User already exists.", responseCode = "406", content = @Content)
    @PreAuthorize("hasAuthority('users:write')")
    @RequestMapping(value = "", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createUser(@RequestBody User user) {
        try {
            userService.createUser(user);
            return new ResponseEntity<>(user, HttpStatus.CREATED);
        } catch (NullEntityException e) {
            return new ResponseEntity<>("Incorrect user description!", HttpStatus.BAD_REQUEST);
        } catch (EntityAlreadyExistsException e) {
            return new ResponseEntity<>("User with login: " + user.getLogin() + " already exists!", HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @Operation(summary = "Get user's info")
    @ApiResponse(description = "Get user's info success.", responseCode = "200",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = User.class)))
    @ApiResponse(description = "User not found.", responseCode = "404", content = @Content)
    @PreAuthorize("hasAuthority('users:read')")
    @RequestMapping(value = "{login}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> readUser(@PathVariable("login") String login) {
        try {
            return new ResponseEntity<>(userService.readUser(login), HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>("User with login:" + login + " not found.", HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Edit user's info")
    @ApiResponse(description = "Edit user's info success.", responseCode = "200",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = User.class)))
    @ApiResponse(description = "Edit user's info failed. Wrong request.", responseCode = "400", content = @Content)
    @ApiResponse(description = "User not found", responseCode = "404", content = @Content)
    @PreAuthorize("hasAuthority('users:write')")
    @RequestMapping(value = "", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateUser(@RequestBody User user) {
        try {
            userService.updateUser(user);
            return new ResponseEntity<>(user, HttpStatus.OK);
        } catch (NullEntityException e) {
            return new ResponseEntity<>("Incorrect user description!", HttpStatus.BAD_REQUEST);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>("User with login:" + user.getLogin() + " not found.", HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Delete user")
    @ApiResponse(description = "Delete user success.", responseCode = "204", content = @Content)
    @ApiResponse(description = "User delete failed. User not found.", responseCode = "404", content = @Content)
    @PreAuthorize("hasAuthority('users:read')")
    @RequestMapping(value = "{login}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> deleteUser(@PathVariable("login") String login) {
        try {
            userService.deleteUser(login);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>("User with login:" + login + " not found.", HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Get list of users")
    @ApiResponse(description = "Get list of users success.", responseCode = "200",
            content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = User.class))))
    @ApiResponse(description = "Users not found.", responseCode = "404", content = @Content)
    @PreAuthorize("hasAuthority('users:read')")
    @RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<User>> getAllUsers() {
        try {
            return new ResponseEntity<>(userService.findAll(), HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
