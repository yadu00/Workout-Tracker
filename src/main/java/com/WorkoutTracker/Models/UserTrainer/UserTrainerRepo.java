package com.WorkoutTracker.Models.UserTrainer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserTrainerRepo extends JpaRepository<UserTrainerModel,Integer> {


    @Query("SELECT ut FROM UserTrainerModel ut WHERE ut.trainer_id = :trainerId")
    List<UserTrainerModel> findByTrainerId(Integer trainerId);

    @Query("SELECT ut FROM UserTrainerModel ut WHERE ut.trainer_id = :trainerId AND ut.user_id =:userId")
    List<UserTrainerModel> findByUserIdAndTrainerId(Integer userId, Integer trainerId);

    @Query("SELECT ut FROM UserTrainerModel ut WHERE ut.user_id = :userId")
    Optional<UserTrainerModel> findByUserId(Integer userId);

    @Query("SELECT ut FROM UserTrainerModel ut WHERE ut.trainer_id = :trainerId")
    List<UserTrainerModel> findByTrainer_Id(Integer trainerId);
}
