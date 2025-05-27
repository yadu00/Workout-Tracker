package com.WorkoutTracker.Dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.Column;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class TrainerDto {

    private Integer trainer_id;
    private String name;
    private String email;
    private String password;
    private String certification;
    private Integer experienceYears;
    private Integer specialization;
    private String specialisationName;
    private String status;
    private Integer rating;

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public Integer getTrainer_id() {
        return trainer_id;
    }

    public void setTrainer_id(Integer trainer_id) {
        this.trainer_id = trainer_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCertification() {
        return certification;
    }

    public void setCertification(String certification) {
        this.certification = certification;
    }

    public Integer getExperienceYears() {
        return experienceYears;
    }

    public void setExperienceYears(Integer experienceYears) {
        this.experienceYears = experienceYears;
    }

    public Integer getSpecialization() {
        return specialization;
    }

    public void setSpecialization(Integer specialization) {
        this.specialization = specialization;
    }

    public String getSpecialisationName() {
        return specialisationName;
    }

    public void setSpecialisationName(String specialisationName) {
        this.specialisationName = specialisationName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
