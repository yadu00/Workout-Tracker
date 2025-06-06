package com.WorkoutTracker.Model.Workouts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface WorkoutRepo extends JpaRepository<WorkoutModel,Integer> {


//    @Query("SELECT w FROM WorkoutModel w WHERE w.user_id = :userId AND w.weekdayId = :weekdayId")
//    List<WorkoutModel> findByUserIdAndWeekdayId(Integer userId, Integer weekdayId);


    @Query("SELECT w FROM WorkoutModel w WHERE w.user_id = :userId AND w.workout_id = :workoutId")
    Optional<WorkoutModel> findByUser_IdAndWorkout_id(Integer userId, Integer workoutId);

    @Query("SELECT COUNT(w) FROM WorkoutModel w WHERE w.user_id = :userId AND w.status = :status")
    Integer countByUserIdAndStatus(@Param("userId") Integer userId, @Param("status") Integer status);



    @Query("SELECT w FROM WorkoutModel w WHERE w.user_id = :userId AND w.id = :Id")
    List<WorkoutModel> findByUserIdAndId(Integer userId, Integer Id);



    @Query("SELECT w FROM WorkoutModel w WHERE w.id = :id")
    List<WorkoutModel> findAllByIdColumn(@Param("id") Integer id);



//    @Query("SELECT w FROM WorkoutModel w WHERE w.user_id = :userId AND w.workout = :workout")
//    List<WorkoutModel> findByUserIdAndWorkout(Integer userId, LocalDate workout);

//    @Query("SELECT w FROM WorkoutModel w WHERE w.user_id = :userId AND w.workout = :workout AND w.trainer_id= :trainerId")
//    List<WorkoutModel> findByUserIdAndWorkoutAndTrainerId(Integer userId, LocalDate workout, Integer trainerId);

}
