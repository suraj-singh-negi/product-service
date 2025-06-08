package com.product_backend.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UserDTO {
    
    private String userId;
    private String name;
    private String password;

}
