package com.WorkoutTracker.Model.Workouts;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
@Entity
@Table(name = "workout_table")
@Data
public class WorkoutModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "workout_id")
    private Integer workout_id;

    @Column(name = "exercise_id")
    private Integer exercise_id;

    @Column(name = "user_id")
    private Integer user_id;

    @Column(name = "trainer_id")
    private Integer trainer_id;

    @Column(name = "status")
    private Integer status;

    @Column(name = "workoutStatus")
    private String workoutStatus;

    @Column(name="created_date")
    private LocalDate created_date;


    @Column(name="updated_date")
    private LocalDate updated_date;

    @Column(name="duration")
    private String duration;

    @Column(name="equipments")
    private String equipments;

    @Column(name = "sets")
    private Integer sets;

    @Column(name = "reps")
    private String reps;

    @Column(name = "weights")
    private String weights;

    @Column(name = "id")
    private Integer id;

    @Column(name = "timeUsed")
    private String timeUsed;

    @Column(name = "repsDone")
    private Integer repsDone;

    @Column(name = "setsDone")
    private Integer setsDone;

    @Column(name="remarks")
    private String remarks;



    public WorkoutModel(){
        this.status=1;
    }

    public Integer getWorkout_id() {
        return workout_id;
    }

    public void setWorkout_id(Integer workout_id) {
        this.workout_id = workout_id;
    }

    public Integer getExercise_id() {
        return exercise_id;
    }

    public void setExercise_id(Integer exercise_id) {
        this.exercise_id = exercise_id;
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

    public LocalDate getCreated_date() {
        return created_date;
    }

    public void setCreated_date(LocalDate created_date) {
        this.created_date = created_date;
    }


    public LocalDate getUpdated_date() {
        return updated_date;
    }

    public void setUpdated_date(LocalDate updated_date) {
        this.updated_date = updated_date;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getEquipments() {
        return equipments;
    }

    public void setEquipments(String equipments) {
        this.equipments = equipments;
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTimeUsed() {
        return timeUsed;
    }

    public void setTimeUsed(String timeUsed) {
        this.timeUsed = timeUsed;
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

    public String getWorkoutStatus() {
        return workoutStatus;
    }

    public void setWorkoutStatus(String workoutStatus) {
        this.workoutStatus = workoutStatus;
    }
}