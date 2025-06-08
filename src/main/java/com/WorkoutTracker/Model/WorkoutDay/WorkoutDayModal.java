package com.WorkoutTracker.Model.WorkoutDay;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Table(name = "Workout_Day_Table")
@Data
public class WorkoutDayModal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "workoutdayId")
    private Integer workoutdayId;

    @Column(name = "day")
    private String day;

    @Column(name = "workoutName")
    private String workoutName;

    @Column(name = "user_id")
    private Integer user_id;

    @Column(name = "trainer_id")
    private Integer trainer_id;

    @Column(name = "status")
    private Integer status;

    @Column(name = "date")
    private LocalDate date;

    public WorkoutDayModal(){
        this.status=1;
    }

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
