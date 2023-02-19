package com.market.entity;
import javax.persistence.*;

    @Entity
    @Table(name="roles")
    public class Role extends BaseEntity {

        @Enumerated(EnumType.STRING)
        private RoleNameEnum name;

        public Role() {
        }
        public Role(RoleNameEnum name) {
            this.name = name;
        }

        public RoleNameEnum getName() {
            return name;
        }

        public void setName(RoleNameEnum name) {
            this.name = name;
        }
    }
