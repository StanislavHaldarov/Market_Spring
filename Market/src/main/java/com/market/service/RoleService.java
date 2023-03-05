package com.market.service;

import com.market.entity.Role;
import com.market.entity.RoleNameEnum;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public interface RoleService {
    void initRoles();
    Role findRole(RoleNameEnum roleNameEnum);
    List<Role> getAllRoles();

}
