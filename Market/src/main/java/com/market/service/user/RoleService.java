package com.market.service.user;

import com.market.entity.Role;
import com.market.util.enums.RoleNameEnum;

import java.util.List;



public interface RoleService {

    Role findRole(RoleNameEnum roleNameEnum);
    List<Role> getAllRoles();

}
