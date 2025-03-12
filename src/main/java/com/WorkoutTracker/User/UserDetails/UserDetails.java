package com.WorkoutTracker.User.UserDetails;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.time.LocalDate;
@CrossOrigin(origins = "http://localhost:8081")

@Entity
@Table(name = "user_health_table")
@Data

public class UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userDetails_id")
    private Integer userDetails_id;

    @Column(name = "user_id")
    private Integer user_id;

    @Column(name = "weight")
    private double weight;

    @Column(name = "height")
    private double height;

    @Column(name = "bmi")
    private double bmi;

    @Column(name = "updated_bmi")
    private double updated_bmi;

    @Column(name = "bmi_date")
    private LocalDate bmi_date;

    @Column(name = "bmi_Updated_date")
    private LocalDate bmi_Updated_date;

    @Column(name = "gender")
    private String gender;


    @Column(name = "goal")
    private String goal;


    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getGoal() {
        return goal;
    }

    public void setGoal(String goal) {
        this.goal = goal;
    }

    public Integer getUserDetails_id() {
        return userDetails_id;
    }

    public void setUserDetails_id(Integer userDetails_id) {
        this.userDetails_id = userDetails_id;
    }

    public double getBmi() {
        return bmi;
    }

    public void setBmi(double bmi) {
        this.bmi = bmi;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public LocalDate getBmi_date() {
        return bmi_date;
    }

    public void setBmi_date(LocalDate bmi_date) {
        this.bmi_date = bmi_date;
    }

    public LocalDate getBmi_Updated_date() {
        return bmi_Updated_date;
    }

    public void setBmi_Updated_date(LocalDate bmi_Updated_date) {
        this.bmi_Updated_date = bmi_Updated_date;
    }

    public double getUpdated_bmi() {
        return updated_bmi;
    }

    public void setUpdated_bmi(double updated_bmi) {
        this.updated_bmi = updated_bmi;
    }
}
