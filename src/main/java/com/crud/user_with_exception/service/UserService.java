package com.crud.user_with_exception.service;

import com.crud.user_with_exception.entity.User;
import com.crud.user_with_exception.entity.dto.UserDTO;
import org.springframework.data.domain.Page;

import java.util.List;

public interface UserService {
    User save(User user);

    User findUserById(Long id);

    List<User> getAllUsers();

    User update(Long id, UserDTO dto);

    void deleteUserById(Long id);

    Page<UserDTO> getAllUsersWithPage(int page, int size);
}
