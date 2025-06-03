package com.WorkoutTracker.Dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.LocalDate;

@JsonInclude(JsonInclude.Include.NON_NULL)

public class WorkoutUpdateDto {
    private String workout_name;
    private Integer exercise_id;
    private Integer workout_id;
    private Integer category_id;
    private String duration;
    private LocalDate Workout;
    private Integer reps;
    private Integer sets;
    private Integer weights;
    private String remarks;
    private String workoutStatus;
    private Integer setsDone;
    private Integer repsDone;

    public Integer getSetsDone() {
        return setsDone;
    }

    public void setSetsDone(Integer setsDone) {
        this.setsDone = setsDone;
    }

    public Integer getRepsDone() {
        return repsDone;
    }

    public void setRepsDone(Integer repsDone) {
        this.repsDone = repsDone;
    }

    public Integer getWorkout_id() {
        return workout_id;
    }

    public void setWorkout_id(Integer workout_id) {
        this.workout_id = workout_id;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Integer getReps() {
        return reps;
    }

    public void setReps(Integer reps) {
        this.reps = reps;
    }

    public Integer getSets() {
        return sets;
    }

    public void setSets(Integer sets) {
        this.sets = sets;
    }

    public Integer getWeights() {
        return weights;
    }

    public void setWeights(Integer weights) {
        this.weights = weights;
    }

    public String getWorkout_name() {
        return workout_name;
    }

    public void setWorkout_name(String workout_name) {
        this.workout_name = workout_name;
    }

    public Integer getExercise_id() {
        return exercise_id;
    }

    public void setExercise_id(Integer exercise_id) {
        this.exercise_id = exercise_id;
    }

    public Integer getCategory_id() {
        return category_id;
    }

    public void setCategory_id(Integer category_id) {
        this.category_id = category_id;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public LocalDate getWorkout() {
        return Workout;
    }

    public void setWorkout(LocalDate workout) {
        Workout = workout;
    }

    public String getWorkoutStatus() {
        return workoutStatus;
    }

    public void setWorkoutStatus(String workoutStatus) {
        this.workoutStatus = workoutStatus;
    }
}
