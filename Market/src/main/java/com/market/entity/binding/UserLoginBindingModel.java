package com.market.entity.binding;

import org.hibernate.validator.constraints.Length;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class UserLoginBindingModel {
    @Length(min=2,message = "Username length must be at least 3 characters long!")
    private String username;
    @Length(min=3,message = "Password length must be at least 3 characters long!")
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;

    }
}
