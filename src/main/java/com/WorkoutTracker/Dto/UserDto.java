package com.WorkoutTracker.Dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.Column;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDto {
    private Integer user_id;
    private String name;
    private String email;
    private String password;

    public UserDto(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
