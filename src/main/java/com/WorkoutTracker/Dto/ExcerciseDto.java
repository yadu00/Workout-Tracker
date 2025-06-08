package com.WorkoutTracker.Dto;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ExcerciseDto {
    private Integer exercise_id;
    private String exercise_name;
    private String exercise_description;
    private String focusarea;
    private String category;
    private Integer sets;
    private Integer reps;
    private double weights;
    private byte[] exerciseImage;

    public byte[] getExerciseImage() {
        return exerciseImage;
    }

    public void setExerciseImage(byte[] exerciseImage) {
        this.exerciseImage = exerciseImage;
    }

    public String getFocusarea() {
        return focusarea;
    }

    public void setFocusarea(String focusarea) {
        this.focusarea = focusarea;
    }

    public Integer getExercise_id() {
        return exercise_id;
    }

    public void setExercise_id(Integer exercise_id) {
        this.exercise_id = exercise_id;
    }

    public String getExercise_name() {
        return exercise_name;
    }

    public void setExercise_name(String exercise_name) {
        this.exercise_name = exercise_name;
    }

    public String getExercise_description() {
        return exercise_description;
    }

    public void setExercise_description(String exercise_description) {
        this.exercise_description = exercise_description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
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
}
