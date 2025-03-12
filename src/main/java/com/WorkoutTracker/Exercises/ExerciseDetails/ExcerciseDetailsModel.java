package com.WorkoutTracker.Exercises.ExerciseDetails;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
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

    @Column(name = "focusarea")
    private String focusarea;

    @Column(name = "category_id")
    private Integer category_id;

    @Column(name = "trainer_id")
    private Integer trainer_id;

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

    public Integer getCategory_id() {
        return category_id;
    }

    public void setCategory_id(Integer category_id) {
        this.category_id = category_id;
    }

    public Integer getTrainer_id() {
        return trainer_id;
    }

    public void setTrainer_id(Integer trainer_id) {
        this.trainer_id = trainer_id;
    }


}
