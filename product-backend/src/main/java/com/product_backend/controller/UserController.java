package com.product_backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.product_backend.dto.UserDTO;
import com.product_backend.response.ApiResponse;
import com.product_backend.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import static com.product_backend.utility.Constants.API_V1;

@Tag(name = "User API")
@RestController
@RequestMapping(API_V1+"/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Operation(summary = "Creates a new user", description = "This endpoint created a user.")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse<?>> createUser(@RequestBody UserDTO userDTO){
        UserDTO savedUserDTO = userService.createUser(userDTO);
        return new ResponseEntity<>(
            new ApiResponse<>(true, "User created successfullly", savedUserDTO), 
            HttpStatus.CREATED);
    }

    @Operation(summary = "Get a user", description = "This endpoint fetches a user with given user id.")
    @GetMapping(value = "/{userId}", consumes = MediaType.APPLICATION_JSON_VALUE,
    produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse<?>> getUser(@PathVariable("userId") String userId){
        UserDTO userDTO = userService.getUser(userId);
        return new ResponseEntity<>(
            new ApiResponse<>(true, "User successfully fetched", userDTO), HttpStatus.OK);
    }

    @Operation(summary = "Update a user", description = "This endpoint updates an existing user.")
    @PutMapping(value = "/{userId}", consumes = MediaType.APPLICATION_JSON_VALUE,
    produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse<?>> updateUser(
        @PathVariable("userId") String userId, @RequestBody UserDTO userDTO){
        UserDTO updatedUserDTO = userService.updateUser(userId, userDTO);
        return new ResponseEntity<>(
            new ApiResponse<>(true, 
                "User updated succesfully", updatedUserDTO), HttpStatus.OK);
    }

    @Operation(summary = "Delete a user", 
        description = "This endpoint deletes a user with given user id.")
    @DeleteMapping(value = "/{userId}", consumes = MediaType.APPLICATION_JSON_VALUE,
    produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse<?>> deleteUser(@PathVariable("userId") String userId){
        userService.deleteUser(userId);
        return ResponseEntity.status(HttpStatus.OK)
            .body(new ApiResponse<>(true, "User deleted successfully", null));
    }
    
}
