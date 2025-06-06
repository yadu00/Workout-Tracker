package com.WorkoutTracker.Model.DailyWorkout;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface DailyWorkoutRepo extends JpaRepository<DailyWorkout,Integer> {



    @Query("SELECT COUNT(d) FROM DailyWorkout d WHERE d.user_id = :userId AND d.trainer_id = :trainerId")
    int countByUser_IdAndTrainer_Id(Integer userId, Integer trainerId);

    @Query("SELECT ut FROM DailyWorkout ut WHERE ut.user_id = :userId")
    List<DailyWorkout> findByUser_Id(Integer userId);

    @Query("SELECT ut FROM DailyWorkout ut WHERE ut.user_id = :userId And ut.date =:date")
    Optional<DailyWorkout> findByUser_IdAndDate(Integer userId, LocalDate date);



    @Query("SELECT ut FROM DailyWorkout ut WHERE ut.user_id = :userId And ut.date =:date")
    List<DailyWorkout> findByUser_IdDate(Integer userId, LocalDate date);
}
