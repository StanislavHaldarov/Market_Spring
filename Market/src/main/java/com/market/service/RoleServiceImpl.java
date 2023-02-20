package com.market.service;

import com.market.entity.Role;
import com.market.entity.RoleNameEnum;
import com.market.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public void initRoles() {
        for (RoleNameEnum r : RoleNameEnum.values()) {
            if (roleRepository.findRoleByName(r) == null) {
                roleRepository.save(new Role(r));
            }
        }
    }
}
