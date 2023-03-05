package com.market.service;

import com.market.entity.RoleNameEnum;
import com.market.entity.User;
import com.market.repository.UserRepository;
import com.market.utility.exception.NotFoundException;
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
        if (userRepository.getUserByEmail(user.getEmail()) == null) {
            user.setRole(roleService.findRole(RoleNameEnum.CUSTOMER));
            System.out.println("CUSTOMER");
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            String encodedPassword = passwordEncoder.encode(user.getPassword());
            user.setEnabled(true);
            user.setPassword(encodedPassword);

            userRepository.save(user);
        } else {
            throw new NotFoundException("Потребител с такъв имейл вече е регистриран!");
        }
    }

    @Override
    public User findUserByUsername(String username, String password) {
        User user = new User();
        user = userRepository.getUserByUsername(username);
        if (verifyPassword(password, user.getPassword())) {
            return user;
        }
        return null;

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

    private boolean verifyPassword(String givenPassword, String hashedPassword) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.matches(givenPassword, hashedPassword);
    }
}
