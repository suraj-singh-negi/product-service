package com.product_backend.mapper;

import org.mapstruct.Mapper;

import com.product_backend.dto.UserDTO;
import com.product_backend.entity.User;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toEntity(UserDTO userDTO);
    UserDTO toDto(User user);
}
