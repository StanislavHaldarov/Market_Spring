package com.market.entity.binding;

import org.hibernate.validator.constraints.Length;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class UserLoginBindingModel {
    @Length(min=2,message = "Потребителското име трябва да съдъража поне 2 или повече символа!")
    private String username;
    @Length(min=3,message = "Паролата трябва да съдържа поне 3 или повече символа!")
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
