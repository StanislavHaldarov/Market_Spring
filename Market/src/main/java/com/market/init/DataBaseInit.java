package com.market.init;

import com.market.entity.Role;
import com.market.entity.RoleNameEnum;
import com.market.entity.productTypes.ProductTypeEnum;
import com.market.entity.productTypes.Type;
import com.market.repository.RoleRepository;
//import com.market.repository.TypeRepository;
import com.market.repository.TypeRepository;
import com.market.service.RoleService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataBaseInit implements CommandLineRunner {

    private final RoleRepository roleRepository;
    private final TypeRepository typeRepository;

    public DataBaseInit(RoleRepository roleRepository, TypeRepository typeRepository) {
        this.roleRepository = roleRepository;
        this.typeRepository = typeRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        this.initRoles();
        this.initTypes();
    }


    private void initRoles() {
        for (RoleNameEnum r : RoleNameEnum.values()) {
            if (roleRepository.findRoleByName(r) == null) {
                roleRepository.save(new Role(r));
            }
        }
    }


    private void initTypes() {
        for (ProductTypeEnum t : ProductTypeEnum.values()) {
            if (typeRepository.findTypeByName(t) == null) {
                typeRepository.save(new Type(t));
            }
        }
    }
}
