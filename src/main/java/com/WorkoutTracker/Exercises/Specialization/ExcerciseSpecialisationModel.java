package com.WorkoutTracker.Exercises.Specialization;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "exercise_specialisation")
@Data
public class ExcerciseSpecialisationModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "specialization_id")
    private Integer specialization_id;

    @Column(name = "specialization_name")
    private String specialization_name;

    public Integer getSpecialization_id() {
        return specialization_id;
    }

    public void setSpecialization_id(Integer specialization_id) {
        this.specialization_id = specialization_id;
    }

    public String getSpecialization_name() {
        return specialization_name;
    }

    public void setSpecialization_name(String specialization_name) {
        this.specialization_name = specialization_name;
    }
}
