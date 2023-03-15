package com.market.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Table(name="users")
public class User extends BaseEntity{
    @Column(nullable = false, unique = true, length = 30)
    @Size(min = 2, max = 30)
    private String username;
    @Column(nullable = false)
    private String password;
    @ManyToOne
    private Role role;
    @Size(max = 50)
    @Column(nullable = false,name = "first_name", length = 50)
    private String firstName;
    @Size(max = 50)
    @Column(nullable = false,name = "last_name", length = 50)
    private String lastName;
    @Column(nullable = false, unique = true, length = 100)
    @Size(max = 100)
    @Email
    private String email;
    @Column(nullable = false)
    @Min(0)
    @Max(99)
    private Integer age;
    @Column(nullable = true)
    @Min(value = 0, message = "Заплатата не може да бъде отрицателно число!")
    private Double salary;
    @Column(nullable = true)
    @Size(min = 7, max = 15)
    private String phoneNumber;
    private boolean enabled;

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public User() {
    }

    public User(String username, String password, Role role, String firstName, String lastName, String email, Integer age, Double salary,String phoneNumber,boolean enabled) {
        this.username = username;
        this.password = password;
        this.role = role;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.age = age;
        this.salary = salary;
        this.phoneNumber = phoneNumber;
        this.enabled = enabled;
    }

    public Double getSalary() {
        return salary;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
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

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
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
