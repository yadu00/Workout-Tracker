package com.WorkoutTracker.Models.Workouts;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "workout_status_table")
@Data
public class WorkoutStatusModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "statusId")
    private Integer statusId;

    @Column(name = "status")
    private String status;

    @Column(name = "workout_id")
    private Integer workout_id;

    public WorkoutStatusModel(){
        this.statusId=1;
    }

    public Integer getStatusId() {
        return statusId;
    }

    public void setStatusId(Integer statusId) {
        this.statusId = statusId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getWorkout_id() {
        return workout_id;
    }

    public void setWorkout_id(Integer workout_id) {
        this.workout_id = workout_id;
    }
}
