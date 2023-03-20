package com.market.service.impl.user;

import com.market.entity.Role;
import com.market.utility.enums.RoleNameEnum;
import com.market.repository.RoleRepository;
import com.market.service.impl.user.RoleServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class RoleServiceImplTests {
    @InjectMocks
    private RoleServiceImpl roleService;
    @Mock
    private RoleRepository roleRepository;
    @Test
    public void testGetAllRoles(){
        List<Role> allRoles = new ArrayList<>();
        allRoles.add(roleService.findRole(RoleNameEnum.EMPLOYEE));
        when(roleRepository.findAll()).thenReturn(allRoles);
        List<Role> testRoles = roleService.getAllRoles();
        assertEquals(allRoles,testRoles);
    }
    @Test
    public void testGetAllRolesWhenListIsEmpty(){
        List<Role> allRoles = new ArrayList<>();
        when(roleRepository.findAll()).thenReturn(allRoles);
        List<Role> testRoles = roleService.getAllRoles();
        assertNull(testRoles);
    }
}
