package com.market.service.user;

import com.market.entity.User;

import java.util.List;


public interface UserService {
    void registerUser(UserServiceModel userServiceModel);
    User findUserByUsername(String username);

    User findUserByUsernameAndPassword(String username, String password);
    List<User> getAllUsers();

    void updateUserRole(User user);
    void deleteUser(Long id);
    User findAuthenticatedUser();
}
