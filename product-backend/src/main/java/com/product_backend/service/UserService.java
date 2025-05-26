package com.product_backend.service;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.product_backend.dto.UserDTO;
import com.product_backend.entity.User;
import com.product_backend.mapper.UserMapper;
import com.product_backend.repository.UserRepository;

@Service
public class UserService {
    
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    public UserDTO createUser(UserDTO userDTO){
        User user = userMapper.toEntity(userDTO);
        String userId = UUID.randomUUID().toString();
        user.setUserId(userId);
        User savedUser = userRepository.save(user);
        return userMapper.toDto(savedUser);
    }

    public UserDTO updateUser(String userId, UserDTO userDTO){
        User user = userMapper.toEntity(userDTO);
        user.setUserId(userId);
        User savedUser = userRepository.save(user);
        return userMapper.toDto(savedUser);
    }

    public UserDTO getUser(String userId){
        Optional<User> user = userRepository.findById(userId);
        return user.isPresent() ? userMapper.toDto(user.get()) : null;
    }

    public void deleteUser(String userId){
        userRepository.deleteById(userId);
    }

}
