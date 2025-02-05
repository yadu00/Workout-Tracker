package com.WorkoutTracker.Excercises.ExcerciseDetails;

import com.WorkoutTracker.Excercises.ExcerciseCategory.ExcerciseCategory;
import com.WorkoutTracker.Trainer.TrainerModel;
import com.WorkoutTracker.User.Registration.UserModel;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "exercise_details_table")
@Data
public class ExcerciseDetailsModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "exercise_id")
    private Integer exercise_id;

    @Column(name = "exercise_name")
    private String exercise_name;

    @Column(name = "exercise_description")
    private String exercise_description;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private ExcerciseCategory category;

    @ManyToOne
    @JoinColumn(name = "trainer_id")
    private TrainerModel trainer;

    @Column(name = "sets")
    private Integer sets;

    @Column(name = "reps")
    private Integer reps;

    @Column(name = "weights")
    private double weights;

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

    public ExcerciseCategory getCategory() {
        return category;
    }

    public void setCategory(ExcerciseCategory category) {
        this.category = category;
    }

    public TrainerModel getTrainer() {
        return trainer;
    }

    public void setTrainer(TrainerModel trainer) {
        this.trainer = trainer;
    }
}
