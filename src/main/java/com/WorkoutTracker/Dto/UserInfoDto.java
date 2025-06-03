package com.WorkoutTracker.Dto;

public class UserInfoDto {
    private Integer user_id;
    private String name;



    public UserInfoDto(Integer user_id, String name) {
        this.user_id = user_id;
        this.name = name;
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
}
