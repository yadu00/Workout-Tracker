package com.WorkoutTracker.Trainer;

import com.WorkoutTracker.User.Registration.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface TrainerRepo extends JpaRepository<TrainerModel,Integer> {
    Optional<TrainerModel> findByEmailAndPassword(String email, String password);

    Optional<TrainerModel> findByEmail(String email);

    @Query("SELECT u FROM TrainerModel u WHERE u.specialization_id = :specializationId")
    List<TrainerModel> findBySpecializationId(Integer specializationId);

}
