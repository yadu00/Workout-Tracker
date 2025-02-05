package com.WorkoutTracker.UserTrainer;

import com.WorkoutTracker.Trainer.TrainerModel;
import com.WorkoutTracker.User.Registration.UserModel;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "user_trainer_table")
@Data
public class UserTrainerModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserModel user;

    @ManyToOne
    @JoinColumn(name = "trainer_id", nullable = false)
    private TrainerModel trainer;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
}
