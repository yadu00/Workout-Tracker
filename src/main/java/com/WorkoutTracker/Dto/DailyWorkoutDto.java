package com.WorkoutTracker.Dto;

import jakarta.persistence.Column;

import java.time.LocalDate;

public class DailyWorkoutDto {
    private Integer workoutdayId;

    private String day;

    private String workoutName;

    private Integer user_id;

    private Integer trainer_id;

    private Integer status;

    private LocalDate date;

    public Integer getWorkoutdayId() {
        return workoutdayId;
    }

    public void setWorkoutdayId(Integer workoutdayId) {
        this.workoutdayId = workoutdayId;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getWorkoutName() {
        return workoutName;
    }

    public void setWorkoutName(String workoutName) {
        this.workoutName = workoutName;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public Integer getTrainer_id() {
        return trainer_id;
    }

    public void setTrainer_id(Integer trainer_id) {
        this.trainer_id = trainer_id;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
