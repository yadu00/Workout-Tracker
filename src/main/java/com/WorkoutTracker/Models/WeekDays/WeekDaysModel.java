package com.WorkoutTracker.Models.WeekDays;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "Week_Days_table")
@Data
public class WeekDaysModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "weekdayId")
    private Integer weekdayId;

    @Column(name = "week")
    private String week;

    @Column(name = "day")
    private String day;

    @Column(name = "name")
    private String name;
    @Column(name = "user_id")
    private Integer user_id;
    @Column(name = "trainer_id")
    private Integer trainer_id;

    @Column(name = "status")
    private String status;

    public Integer getWeekdayId() {
        return weekdayId;
    }

    public void setWeekdayId(Integer weekdayId) {
        this.weekdayId = weekdayId;
    }

    public String getWeek() {
        return week;
    }

    public void setWeek(String week) {
        this.week = week;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
