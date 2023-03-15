package com.market.entity.job;

import com.market.entity.BaseEntity;
import com.market.entity.User;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name = "job_applications")
public class JobApplication extends BaseEntity {
    @Column(nullable = false)
    @Size(min = 100, max = 200)
    private String description;
    @Column(nullable = false, name = "phone_number")
    @Size(min = 7, max = 15)
    private String phoneNumber;
    @ManyToOne
    private User user;

    public JobApplication() {
    }

    public JobApplication(String description, String phoneNumber, User user) {
        this.description = description;
        this.phoneNumber = phoneNumber;
        this.user = user;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
