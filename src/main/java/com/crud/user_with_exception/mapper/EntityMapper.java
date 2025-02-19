package com.crud.user_with_exception.mapper;

import com.crud.user_with_exception.entity.User;
import com.crud.user_with_exception.entity.dto.UserDTO;
import org.springframework.stereotype.Component;

@Component
public class EntityMapper {
    public User convertToUser(UserDTO userDTO) {
        User user = new User();
        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        return user;
    }

    public UserDTO convertToUserDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setName(user.getName());
        userDTO.setEmail(user.getEmail());
        return userDTO;
    }
}
