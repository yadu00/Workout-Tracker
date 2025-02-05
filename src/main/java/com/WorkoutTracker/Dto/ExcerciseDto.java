package com.WorkoutTracker.Dto;

import com.WorkoutTracker.Excercises.ExcerciseCategory.ExcerciseCategory;
import com.WorkoutTracker.Trainer.TrainerModel;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

public class ExcerciseDto {
    private Integer exercise_id;
    private String exercise_name;
    private String exercise_description;
    private ExcerciseCategory category;
    private Integer sets;
    private Integer reps;
    private double weights;


    public double getWeights() {
        return weights;
    }

    public void setWeights(double weights) {
        this.weights = weights;
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

    public ExcerciseCategory getCategory() {
        return category;
    }

    public void setCategory(ExcerciseCategory category) {
        this.category = category;
    }

    public String getExercise_description() {
        return exercise_description;
    }

    public void setExercise_description(String exercise_description) {
        this.exercise_description = exercise_description;
    }

    public String getExercise_name() {
        return exercise_name;
    }

    public void setExercise_name(String exercise_name) {
        this.exercise_name = exercise_name;
    }

    public Integer getExercise_id() {
        return exercise_id;
    }

    public void setExercise_id(Integer exercise_id) {
        this.exercise_id = exercise_id;
    }
}
