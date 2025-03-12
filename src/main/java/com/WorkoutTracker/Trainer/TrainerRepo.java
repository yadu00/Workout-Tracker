package com.WorkoutTracker.Trainer;

import com.WorkoutTracker.User.Registration.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TrainerRepo extends JpaRepository<TrainerModel,Integer> {
    Optional<TrainerModel> findByEmailAndPassword(String email, String password);

    Optional<TrainerModel> findByEmail(String email);

}
