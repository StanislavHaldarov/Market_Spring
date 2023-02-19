package com.market.init;

import com.market.service.RoleService;
import com.market.service.TypeService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataBaseInit implements CommandLineRunner {

    private final RoleService roleService;
    private final TypeService typeService;

    public DataBaseInit(RoleService roleService, TypeService typeService) {
        this.roleService = roleService;
        this.typeService = typeService;
    }

    @Override
    public void run(String... args) throws Exception {
        roleService.initRoles();
        typeService.initTypes();
    }
}
