package com.WorkoutTracker.User.Registration;

import com.WorkoutTracker.Exercises.ExerciseDetails.ExcerciseDetailsModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepo extends JpaRepository<UserModel,Integer> {
    Optional<UserModel> findByEmailAndPassword(String email, String password);

    Optional<UserModel> findByEmail(String email);

    @Query("SELECT u FROM UserModel u WHERE u.user_id = :userId")
    List<UserModel> findByUserId(Integer userId);

}
