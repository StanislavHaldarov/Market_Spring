package com.market.service.impl.user;

import com.market.utility.enums.RoleNameEnum;
import com.market.entity.User;
import com.market.repository.UserRepository;
import com.market.service.user.RoleService;
import com.market.service.user.UserService;
import com.market.service.user.UserServiceModel;
import com.market.utility.exception.EmailAlreadyExistsException;
import com.market.utility.exception.UsernameAlreadyExistsException;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleService roleService;
    private final ModelMapper modelMapper;

    public UserServiceImpl(UserRepository userRepository, RoleService roleService, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.roleService = roleService;
        this.modelMapper = modelMapper;
    }


    @Override
    public void registerUser(UserServiceModel userServiceModel) {
        User user = modelMapper.map(userServiceModel, User.class);
        if (userRepository.getUserByUsername(user.getUsername()) != null) {
            throw new UsernameAlreadyExistsException("Използваното потребителско име вече е заето!");
        }
        if (userRepository.getUserByEmail(user.getEmail()) != null) {
            throw new EmailAlreadyExistsException("Потребител с такъв имейл вече е регистриран!");
        }
        user.setRole(roleService.findRole(userServiceModel.getRole().getName()));
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(user.getPassword());

        user.setEnabled(true);
        user.setPassword(encodedPassword);

        userRepository.save(user);

    }

    @Override
    public User findUserByUsername(String username) {
        return userRepository.getUserByUsername(username);
    }


    @Override
    public UserServiceModel findUserByUsernameAndPassword(String username, String password) {
        return userRepository.findByUsernameAndPassword(username, password)
                .map(user -> modelMapper.map(user, UserServiceModel.class))
                .orElse(null);
    }

    @Override
    public List<User> getAllUsers() {
        List<User> allUsers = userRepository.findAll();
        if (allUsers.isEmpty()) {
            return null;
        }
        return allUsers;
    }

    @Override
    public List<User> getAllEmployees() {
        List<User> allEmployees = userRepository.findAll();
        allEmployees.removeIf(user -> user.getRole().getName() != RoleNameEnum.EMPLOYEE);
        if (allEmployees.isEmpty()) {
            return null;
        }
        return allEmployees;
    }

    @Override
    public void updateUserRole(User user) {
        User existingUser = userRepository.findById(user.getId()).orElse(null);
        if (existingUser != null) {
            existingUser.setRole(user.getRole());
            userRepository.save(existingUser);
        }
    }

    @Override
    public void deleteUser(Long id) {
        Optional<User> existingUser = userRepository.findById(id);
        if (existingUser.isPresent()) {
            userRepository.deleteById(id);
        }
    }

    @Override
    public void updateSalaryById(Long id, Double salary) {
        Optional<User> existingEmployee = userRepository.findById(id);
        if (existingEmployee.isPresent()) {
            User employee = existingEmployee.get();
            employee.setSalary(salary);
            userRepository.save(employee);
        }

    }

    @Override
    public void fireEmployeeById(Long id) {
        Optional<User> existingEmployee = userRepository.findById(id);
        if (existingEmployee.isPresent()) {
            User employee = existingEmployee.get();
            employee.setRole(roleService.findRole(RoleNameEnum.CUSTOMER));
            employee.setSalary(null);
            userRepository.save(employee);
        }

    }
}
