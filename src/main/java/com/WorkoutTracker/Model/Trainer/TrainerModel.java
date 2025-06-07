package com.WorkoutTracker.Model.Trainer;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;


@Entity
@Table(name = "trainer_table")
@Data
public class TrainerModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "trainer_id")
    private Integer trainer_id;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "certification")
    private String certification;

    @Column(name = "experienceYears")
    private Integer experienceYears;

    @Column(name = "specialization_id")
    private Integer specialization_id;

    @Column(name = "created_date")
    private LocalDate created_date;

    @Column(name = "achievements")
    private String achievements;


    @Column(name = "AccountStatus")
    private Integer statusID;

    @Column(columnDefinition = "TEXT")
    private String about;


    @Column(name = "Mobile")
    private String mobile;

    @Column(name = "salary")
    private Double salary;


    @Lob
    @Column(name = "certificationImage")
    private byte[] certificationImage;


    public TrainerModel(){
        this.statusID=1;
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

    public Integer getSpecialization_id() {
        return specialization_id;
    }

    public void setSpecialization_id(Integer specialization_id) {
        this.specialization_id = specialization_id;
    }

    public LocalDate getCreated_date() {
        return created_date;
    }

    public void setCreated_date(LocalDate created_date) {
        this.created_date = created_date;
    }

    public String getAchievements() {
        return achievements;
    }

    public void setAchievements(String achievements) {
        this.achievements = achievements;
    }


    public Integer getStatusID() {
        return statusID;
    }

    public void setStatusID(Integer statusID) {
        this.statusID = statusID;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }


    public byte[] getCertificationImage() {
        return certificationImage;
    }

    public void setCertificationImage(byte[] certificationImage) {
        this.certificationImage = certificationImage;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }
}