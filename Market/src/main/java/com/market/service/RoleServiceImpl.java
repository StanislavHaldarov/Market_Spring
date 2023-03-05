package com.market.service;

import com.market.entity.Role;
import com.market.entity.RoleNameEnum;
import com.market.entity.User;
import com.market.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }


    @Override
    public void initRoles() {
        if(roleRepository.count()==0){
            Role customer = new Role(RoleNameEnum.CUSTOMER);
            Role employee = new Role(RoleNameEnum.EMPLOYEE);
            Role admin = new Role(RoleNameEnum.ADMIN);

            roleRepository.save(customer);
            roleRepository.save(employee);
            roleRepository.save(admin);
        }

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
