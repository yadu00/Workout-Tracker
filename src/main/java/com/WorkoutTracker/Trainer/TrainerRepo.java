package com.WorkoutTracker.Trainer;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TrainerRepo extends JpaRepository<TrainerModel,Integer> {
    Optional<TrainerModel> findByEmailAndPassword(String email, String password);

}
