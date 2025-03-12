package com.WorkoutTracker.Workouts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.List;

public interface WorkoutRepo extends JpaRepository<WorkoutModel,Integer> {


    @Query("SELECT w FROM WorkoutModel w WHERE w.user_id = :userId AND w.workout = :workout")
    List<WorkoutModel> findByUserIdAndWorkout(Integer userId, LocalDate workout);

    @Query("SELECT w FROM WorkoutModel w WHERE w.user_id = :userId AND w.workout = :workout AND w.trainer_id= :trainerId")
    List<WorkoutModel> findByUserIdAndWorkoutAndTrainerId(Integer userId, LocalDate workout, Integer trainerId);

}
