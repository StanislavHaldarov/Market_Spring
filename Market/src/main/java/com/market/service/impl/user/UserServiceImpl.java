package com.market.service.impl.user;

import com.market.entity.User;
import com.market.repository.UserRepository;
import com.market.service.user.RoleService;
import com.market.service.user.UserService;
import com.market.service.user.UserServiceModel;
import com.market.utility.exception.EmailAlreadyExistsException;
import com.market.utility.exception.PasswordVerificationException;
import com.market.utility.exception.UsernameAlreadyExistsException;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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
    public User findUserByUsernameAndPassword(String username, String password) {
        User user;
        user = userRepository.getUserByUsername(username);
        if(user==null){
            throw new UsernameNotFoundException("Невалидно потребителско име!");
        }
        if (verifyPassword(password, user.getPassword())) {
            return user;
        }
        throw new PasswordVerificationException("Неправилна парола!");
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

    @Override
    public User findAuthenticatedUser() {
         Authentication auth = SecurityContextHolder.getContext().getAuthentication();
         if(auth.isAuthenticated()){
             return userRepository.getUserByUsername(auth.getName());
         } else {
             return  null;
         }
    }

    private boolean verifyPassword(String givenPassword, String hashedPassword) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.matches(givenPassword, hashedPassword);
    }
}
