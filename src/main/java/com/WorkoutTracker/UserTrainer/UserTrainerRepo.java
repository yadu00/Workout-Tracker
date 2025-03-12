package com.WorkoutTracker.UserTrainer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserTrainerRepo extends JpaRepository<UserTrainerModel,Integer> {


    @Query("SELECT ut FROM UserTrainerModel ut WHERE ut.trainer_id = :trainerId")
    List<UserTrainerModel> findByTrainerId(Integer trainerId);

}
