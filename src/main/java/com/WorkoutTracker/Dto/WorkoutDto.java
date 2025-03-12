package com.WorkoutTracker.Dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.data.relational.core.sql.In;
@JsonInclude(JsonInclude.Include.NON_NULL)
public class WorkoutDto {


    private String workout_name;
    private String excercise_name;
    private Integer sets;
    private Integer reps;
    private double weights;
    private String duration;


    public String getWorkout_name() {
        return workout_name;
    }

    public void setWorkout_name(String workout_name) {
        this.workout_name = workout_name;
    }

    public String getExcercise_name() {
        return excercise_name;
    }

    public void setExcercise_name(String excercise_name) {
        this.excercise_name = excercise_name;
    }

    public Integer getSets() {
        return sets;
    }

    public void setSets(Integer sets) {
        this.sets = sets;
    }

    public Integer getReps() {
        return reps;
    }

    public void setReps(Integer reps) {
        this.reps = reps;
    }

    public double getWeights() {
        return weights;
    }

    public void setWeights(double weights) {
        this.weights = weights;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }
}
