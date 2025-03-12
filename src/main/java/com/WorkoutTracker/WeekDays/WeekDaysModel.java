package com.WorkoutTracker.WeekDays;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "WeekDays_table")
@Data
public class WeekDaysModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "dayId")
    private Integer dayId;

    @Column(name = "day")
    private String day;

    public Integer getDayId() {
        return dayId;
    }

    public void setDayId(Integer dayId) {
        this.dayId = dayId;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }
}
