package com.market.service;

import com.market.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;


public interface UserService {
    void registerUser(UserServiceModel userServiceModel);

    User findUserByUsername(String username, String password);
    List<User> getAllUsers();

    void updateUserRole(User user);
    void deleteUser(Long id);
}
