package com.market.service.impl.user;


import com.market.entity.Role;
import com.market.utility.enums.RoleNameEnum;

import com.market.repository.RoleRepository;
import com.market.service.user.RoleService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }


    @Override
    public List<Role> getAllRoles() {
        List<Role> allRoles = roleRepository.findAll();
        if (allRoles.isEmpty()) {
            return null;
        }
        return allRoles;
    }

    @Override
    public Role findRole(RoleNameEnum roleNameEnum) {
        return roleRepository.findByName(roleNameEnum).orElse(null);
    }

}
