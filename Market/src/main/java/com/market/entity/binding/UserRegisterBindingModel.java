package com.market.entity.binding;

import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

public class UserRegisterBindingModel {
    @Length(min = 2, message = "Потребителското име трябва да съдъража поне 2 или повече символа!")
    private String username;
    @Length(min = 3, message = "Паролата трябва да съдържа поне 3 или повече символа!")
    private String password;
    private String confirmPassword;
    @Length(max=50, message = "Името може да съдържа максимум 50 символа!")
    private String firstName;
    @Length(max=50, message = "Фамилията може да съдържа максимум 50 символа!")
    private String lastName;
    @Email(message = "Моля въведете валиден имейл, който включва \"@\"!")
    private String email;
    @Min(value = 0, message = "Допостими са стойности между 0 и 99!")
    @Max(value = 99, message = "Допостими са стойности между 0 и 99!")
    private Integer age;

    public UserRegisterBindingModel() {

    }

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

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
