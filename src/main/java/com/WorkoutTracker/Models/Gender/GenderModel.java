package com.WorkoutTracker.Models.Gender;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "gender_table")
@Data
public class GenderModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "gender_id")
    private Integer gender_id;

    @Column(name = "genderName")
    private String genderName;

    public Integer getGender_id() {
        return gender_id;
    }

    public void setGender_id(Integer gender_id) {
        this.gender_id = gender_id;
    }

    public String getGenderName() {
        return genderName;
    }

    public void setGenderName(String genderName) {
        this.genderName = genderName;
    }
}
