package com.market.service;

import com.market.entity.Role;
import com.market.entity.RoleNameEnum;
import com.market.repository.RoleRepository;
import org.springframework.stereotype.Service;


@Service
public class RoleServiceImpl implements RoleService{
    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public void initRoles() {
        if(roleRepository.count() == 0){
            Role employee = new Role(RoleNameEnum.EMPLOYEE);
            Role customer = new Role(RoleNameEnum.CUSTOMER);

            roleRepository.save(employee);
            roleRepository.save(customer);
        }
    }
}
