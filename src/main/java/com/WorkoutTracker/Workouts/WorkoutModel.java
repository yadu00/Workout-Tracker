package com.WorkoutTracker.Workouts;

import com.WorkoutTracker.Excercises.ExcerciseCategory.ExcerciseCategory;
import com.WorkoutTracker.Trainer.TrainerModel;
import com.WorkoutTracker.User.Registration.UserModel;
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

    @Column(name = "workout_name")
    private String workout_name;

    @Column(name = "workout_description")
    private String workout_description;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserModel user;

    @ManyToOne
    @JoinColumn(name = "trainer_id")
    private TrainerModel trainer;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private ExcerciseCategory category;

    @Column(name = "status")
    private String status;

    @Column(name="created_date")
    private LocalDate created_date;

    @Column(name = "scheduled_day")
    private String scheduled_day;

    @Column(name="updated_date")
    private LocalDate updated_date;

    public Integer getWorkout_id() {
        return workout_id;
    }

    public void setWorkout_id(Integer workout_id) {
        this.workout_id = workout_id;
    }

    public String getWorkout_name() {
        return workout_name;
    }

    public void setWorkout_name(String workout_name) {
        this.workout_name = workout_name;
    }

    public String getWorkout_description() {
        return workout_description;
    }

    public void setWorkout_description(String workout_description) {
        this.workout_description = workout_description;
    }

    public UserModel getUser() {
        return user;
    }

    public void setUser(UserModel user) {
        this.user = user;
    }

    public TrainerModel getTrainer() {
        return trainer;
    }

    public void setTrainer(TrainerModel trainer) {
        this.trainer = trainer;
    }

    public ExcerciseCategory getCategory() {
        return category;
    }

    public void setCategory(ExcerciseCategory category) {
        this.category = category;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDate getCreated_date() {
        return created_date;
    }

    public void setCreated_date(LocalDate created_date) {
        this.created_date = created_date;
    }

    public String getScheduled_day() {
        return scheduled_day;
    }

    public void setScheduled_day(String scheduled_day) {
        this.scheduled_day = scheduled_day;
    }

    public LocalDate getUpdated_date() {
        return updated_date;
    }

    public void setUpdated_date(LocalDate updated_date) {
        this.updated_date = updated_date;
    }
}