package com.market.service.user;

import com.market.entity.User;

import java.util.List;


public interface UserService {
    void registerUser(UserServiceModel userServiceModel);

    User findUserByUsername(String username);
    User findAuthenticatedUser();

    UserServiceModel findUserByUsernameAndPassword(String username, String password);
    List<User> getAllUsers();

    List<User> getAllEmployees();

    void updateUserRole(User user);
    void deleteUser(Long id);

    void updateSalaryById(Long id, Double salary);
    void fireEmployeeById(Long id);

    List<User> sortEmployees(List<User> employees, String sort);
}
