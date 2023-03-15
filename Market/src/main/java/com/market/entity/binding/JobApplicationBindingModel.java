package com.market.entity.binding;

import org.hibernate.validator.constraints.Length;

public class JobApplicationBindingModel {
    @Length(min=100,max=200, message="Описанието трябва да бъде в рамките на 100-200 думи!")
    private String description;
    @Length(min=7,max=15, message = "Невалиден телефонен номер!")
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
