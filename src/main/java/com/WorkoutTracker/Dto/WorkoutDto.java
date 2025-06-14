package com.WorkoutTracker.Dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.data.relational.core.sql.In;

import java.time.LocalDate;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class WorkoutDto {

    private Integer workout_id;
    private String excercise_name;
    private Integer sets;
    private String reps;
    private String weights;
    private String equipments;
    private String duration;
    private Integer status;
    private LocalDate date;
    private String focusarea;

    private String workoutStatus;
    private Integer repsDone;
    private Integer setsDone;
    private String remarks;

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

    public String getWorkoutStatus() {
        return workoutStatus;
    }

    public void setWorkoutStatus(String workoutStatus) {
        this.workoutStatus = workoutStatus;
    }

    public Integer getRepsDone() {
        return repsDone;
    }

    public void setRepsDone(Integer repsDone) {
        this.repsDone = repsDone;
    }

    public Integer getSetsDone() {
        return setsDone;
    }

    public void setSetsDone(Integer setsDone) {
        this.setsDone = setsDone;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
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

    public String getReps() {
        return reps;
    }

    public void setReps(String reps) {
        this.reps = reps;
    }

    public String getWeights() {
        return weights;
    }

    public void setWeights(String weights) {
        this.weights = weights;
    }

    public String getEquipments() {
        return equipments;
    }

    public void setEquipments(String equipments) {
        this.equipments = equipments;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public Integer getWorkout_id() {
        return workout_id;
    }

    public void setWorkout_id(Integer workout_id) {
        this.workout_id = workout_id;
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
