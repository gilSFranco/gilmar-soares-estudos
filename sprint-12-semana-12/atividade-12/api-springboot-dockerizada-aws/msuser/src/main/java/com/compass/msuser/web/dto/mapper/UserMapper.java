package com.compass.msuser.web.dto.mapper;

import com.compass.msuser.entities.User;
import com.compass.msuser.web.dto.ResponseUserDTO;
import com.compass.msuser.web.dto.UserDTO;
import org.modelmapper.ModelMapper;

public class UserMapper {
    private UserMapper() {
    }

    public static ResponseUserDTO toDto(User user) {
        return new ModelMapper().map(user, ResponseUserDTO.class);
    }

    public static User toUser(UserDTO userDTO) {
        return new ModelMapper().map(userDTO, User.class);
    }
}
