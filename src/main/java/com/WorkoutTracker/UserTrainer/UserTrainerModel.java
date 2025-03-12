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

    @Column(name = "user_id")
    private Integer user_id;

    @Column(name = "trainer_id")
    private Integer trainer_id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
}
