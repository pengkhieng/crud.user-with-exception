package com.crud.user_with_exception.service.Impl;

import com.crud.user_with_exception.entity.User;
import com.crud.user_with_exception.entity.dto.UserDTO;
import com.crud.user_with_exception.exception.EmailAlreadyExistsException;
import com.crud.user_with_exception.exception.UserNotFoundException;
import com.crud.user_with_exception.mapper.EntityMapper;
import com.crud.user_with_exception.repository.UserRepository;
import com.crud.user_with_exception.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EntityMapper entityMapper;

    @Override
    public User save(User user) {
        // Check if email already exists
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new EmailAlreadyExistsException(user.getEmail());
        }
        return userRepository.save(user);
    }

    @Override
    public User findUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User with ID " + id + " not found"));
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User update(Long id, UserDTO dto) {
        User user = findUserById(id);
        if (user == null) {
            throw new UserNotFoundException("User not found with id: " + id);
        }
        user = entityMapper.convertToUser(dto);
        user.setId(id);

        return userRepository.save(user);
    }

    @Override
    public void deleteUserById(Long id) {
        User user = findUserById(id);
        if (user != null) {
            userRepository.delete(user);
        }
    }

    @Override
    public Page<UserDTO> getAllUsersWithPage(int page, int size) {
        Page<User> usersPage = userRepository.findAll(PageRequest.of(page, size));
        return usersPage.map(entityMapper::convertToUserDTO);
    }
}