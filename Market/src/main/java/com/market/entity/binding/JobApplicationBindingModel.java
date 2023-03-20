package com.market.entity.binding;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Pattern;

public class JobApplicationBindingModel {
    @Length(min=100,max=200, message="Описанието трябва да бъде в рамките на 100-200 думи!")
    private String description;
    @Length(min=7,max=15, message = "Телефонният номер може да съдържа от 7 до 15 символа!\n")
    @Pattern(regexp = "\\d*", message = "Телефонният номер трябва да съдържа само числа!")
    private String phoneNumber;

    public JobApplicationBindingModel() {
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
