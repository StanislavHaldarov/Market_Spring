package com.market.service.impl.user;

import com.market.entity.Role;
import com.market.util.enums.RoleNameEnum;
import com.market.entity.User;
import com.market.repository.RoleRepository;
import com.market.repository.UserRepository;
import com.market.service.user.RoleService;
import com.market.service.user.UserServiceModel;
import com.market.util.exception.EmailAlreadyExistsException;
import com.market.util.exception.UsernameAlreadyExistsException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplTests {


    @InjectMocks
    private UserServiceImpl userService;
    @Mock
    private ModelMapper modelMapper;
    @Mock
    private RoleService roleService;
    @Mock
    private UserRepository userRepository;
    @Mock
    private RoleRepository roleRepository;

    @Test
    public void testFindUserByUsername() {
        String testUsername = "testUsername";
        User user = new User();
        when(userRepository.getUserByUsername(testUsername)).thenReturn(user);
        User testUser = userService.findUserByUsername(testUsername);
        assertEquals(user, testUser);
    }

    @Test
    public void testFindUserByUsernameAndPassword() {
        String testUsername = "testUsername";
        String testPassword = "testPassword";
        User testUser = new User();
        UserServiceModel uSM = new UserServiceModel();
        when(userRepository.findByUsernameAndPassword(testUsername, testPassword))
                .thenReturn(Optional.of(testUser));
        when(modelMapper.map(testUser, UserServiceModel.class))
                .thenReturn(uSM);
        UserServiceModel testUSM = userService
                .findUserByUsernameAndPassword(testUsername, testPassword);
        assertEquals(uSM, testUSM);

    }

    @Test
    public void testRegisterUserIfSuccessful() {
        UserServiceModel userServiceModel = getTestUserServiceModel();
        User userToSave = getTestUser(userServiceModel);
        when(modelMapper.map(userServiceModel, User.class)).thenReturn(userToSave);

        userService.registerUser(userServiceModel);

        verify(modelMapper, Mockito.times(1))
                .map(userServiceModel, User.class);
        verify(userRepository, Mockito.times(1))
                .save(userToSave);
    }

    @Test
    public void testRegisterUserWhenUsernameAlreadyExists() {
        UserServiceModel userServiceModel = getTestUserServiceModel();
        User userToSave = getTestUser(userServiceModel);
        when(modelMapper.map(userServiceModel, User.class)).thenReturn(userToSave);
        when(userRepository.getUserByUsername(userToSave.getUsername()))
                .thenReturn(userToSave);

        assertThrows(UsernameAlreadyExistsException.class,
                () -> userService.registerUser(userServiceModel));
    }

    @Test
    public void testRegisterUserWhenEmailAlreadyExists() {
        UserServiceModel userServiceModel = getTestUserServiceModel();
        User userToSave = getTestUser(userServiceModel);
        when(modelMapper.map(userServiceModel, User.class)).thenReturn(userToSave);
        when(userRepository.getUserByEmail(userToSave.getEmail()))
                .thenReturn(userToSave);

        assertThrows(EmailAlreadyExistsException.class,
                () -> userService.registerUser(userServiceModel));
    }

    @Test
    public void testGetAllUsers() {
        List<User> allUsers = new ArrayList<>();
        User testUser = new User();
        allUsers.add(testUser);
        when(userRepository.findAll()).thenReturn(allUsers);
        List<User> testUsers = userService.getAllUsers();
        assertEquals(allUsers, testUsers);
    }

    @Test
    public void testGetAllUsersWhenUserListIsEmpty() {
        List<User> allUsers = new ArrayList<>();
        when(userRepository.findAll()).thenReturn(allUsers);
        List<User> testUsers = userService.getAllUsers();
        assertNull(testUsers);
    }

    @Test
    public void testGetAllEmployees() {
        List<User> allEmployees = new ArrayList<>();
        User testEmployee = new User();
        testEmployee.setRole(new Role(RoleNameEnum.EMPLOYEE));
        allEmployees.add(testEmployee);
        when(userRepository.findAll()).thenReturn(allEmployees);
        List<User> testUsers = userService.getAllEmployees();
        assertEquals(allEmployees, testUsers);
    }

    @Test
    public void testGetAllEmployeesWhenEmployeeListIsEmpty() {
        List<User> allEmployees = new ArrayList<>();
        when(userRepository.findAll()).thenReturn(allEmployees);
        List<User> testUsers = userService.getAllEmployees();
        assertNull(testUsers);
    }

    @Test
    public void testUpdateUserRole() {
        User testUser = new User();
        testUser.setId(1L);
        testUser.setRole(new Role(RoleNameEnum.MANAGER));
        when(userRepository.findById(testUser.getId()))
                .thenReturn(Optional.of(testUser));
        when(userRepository.save(testUser))
                .thenReturn(testUser);
        userService.updateUserRole(testUser);
        verify(userRepository, Mockito.times(1))
                .findById(testUser.getId());
        verify(userRepository, Mockito.times(1))
                .save(testUser);

    }

    @Test
    public void testDeleteUser() {
        Long testId = 1L;
        when(userRepository.findById(testId))
                .thenReturn(Optional.of(new User()));
        userService.deleteUser(testId);
        verify(userRepository, Mockito.times(1))
                .findById(testId);
        verify(userRepository, Mockito.times(1))
                .deleteById(testId);

    }

    @Test
    public void testUpdateSalaryById() {
        Long testId = 1L;
        Double testSalary = 1.0;
        User employee = new User();
        employee.setId(testId);
        employee.setSalary(testSalary);
        when(userRepository.findById(testId))
                .thenReturn(Optional.of(employee));
        when(userRepository.save(employee))
                .thenReturn(employee);
        userService.updateSalaryById(testId, testSalary);
        verify(userRepository, Mockito.times(1))
                .findById(testId);
        verify(userRepository, Mockito.times(1))
                .save(employee);

    }

    @Test
    public void testFireEmployeeById() {
        Long testId = 1L;
        User employee = new User();
        employee.setId(testId);
        when(userRepository.findById(testId))
                .thenReturn(Optional.of(employee));
        when(userRepository.save(employee))
                .thenReturn(employee);
        userService.fireEmployeeById(testId);
        verify(userRepository, Mockito.times(1))
                .findById(testId);
        verify(userRepository, Mockito.times(1))
                .save(employee);

    }

    private UserServiceModel getTestUserServiceModel() {
        UserServiceModel userServiceModel = new UserServiceModel();
        userServiceModel.setId(1L);
        userServiceModel.setUsername("testUsername");
        userServiceModel.setPassword("testPassword");
        userServiceModel.setEmail("test@test.test");
        userServiceModel.setFirstName("testFirstName");
        userServiceModel.setLastName("testLastName");
        userServiceModel.setRole(new Role(RoleNameEnum.CUSTOMER));
        userServiceModel.setAge(19);
        userServiceModel.setEnabled(true);
        return userServiceModel;
    }

    private User getTestUser(UserServiceModel userServiceModel) {
        User testUser = new User();
        testUser.setId(1L);
        testUser.setUsername("testUsername");
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode("testPassword");
        testUser.setPassword(encodedPassword);
        testUser.setEmail("test@test.test");
        testUser.setFirstName("testFirstName");
        testUser.setLastName("testLastName");
        testUser.setAge(19);
        testUser.setRole(roleService.findRole(userServiceModel.getRole().getName()));
        testUser.setEnabled(true);
        return testUser;
    }

}
