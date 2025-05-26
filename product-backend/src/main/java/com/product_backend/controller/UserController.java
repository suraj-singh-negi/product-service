package com.product_backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<ApiResponse<?>> createUser(@RequestBody UserDTO userDTO){
        UserDTO savedUserDTO = userService.createUser(userDTO);
        return new ResponseEntity<>(
            new ApiResponse<>(true, "User created successfullly", savedUserDTO), 
            HttpStatus.CREATED);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<ApiResponse<?>> getUser(@PathVariable("userId") String userId){
        UserDTO userDTO = userService.getUser(userId);
        return new ResponseEntity<>(
            new ApiResponse<>(true, "User successfully fetched", userDTO), HttpStatus.OK);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<ApiResponse<?>> updateUser(
        @PathVariable("userId") String userId, @RequestBody UserDTO userDTO){
        UserDTO updatedUserDTO = userService.updateUser(userId, userDTO);
        return new ResponseEntity<>(
            new ApiResponse<>(true, "User updated succesfully", updatedUserDTO), HttpStatus.OK);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<ApiResponse<?>> deleteUser(@PathVariable("userId") String userId){
        userService.deleteUser(userId);
        return ResponseEntity.status(HttpStatus.OK)
            .body(new ApiResponse<>(true, "User deleted successfully", null));
    }
    
}
