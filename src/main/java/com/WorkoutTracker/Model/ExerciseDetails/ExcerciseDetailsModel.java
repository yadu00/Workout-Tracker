package com.WorkoutTracker.Model.ExerciseDetails;

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



    @Column(name = "focusarea")
    private String focusarea;


    @Column(name = "trainer_id")
    private Integer trainer_id;

    @Lob
    @Column(name = "exerciseImage")
    private byte[] exerciseImage;


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


    public Integer getTrainer_id() {
        return trainer_id;
    }

    public void setTrainer_id(Integer trainer_id) {
        this.trainer_id = trainer_id;
    }

    public byte[] getExerciseImage() {
        return exerciseImage;
    }

    public void setExerciseImage(byte[] exerciseImage) {
        this.exerciseImage = exerciseImage;
    }
}
