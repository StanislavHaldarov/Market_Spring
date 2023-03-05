package com.market.service;


import com.market.entity.Role;
import com.market.entity.RoleNameEnum;
import com.market.entity.User;

import com.market.repository.RoleRepository;

import java.util.List;


public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }


    @Override
    public List<Role> getAllRoles() {
        List<Role> allRoles = roleRepository.findAll();
        if(allRoles.isEmpty())
        {
            return null;
        }
        return allRoles;
    }

    @Override
    public Role findRole(RoleNameEnum roleNameEnum) {
        return roleRepository.findByName(roleNameEnum).orElse(null);
    }

}
