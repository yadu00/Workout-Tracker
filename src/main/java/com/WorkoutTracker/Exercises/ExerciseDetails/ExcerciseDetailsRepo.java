package com.WorkoutTracker.Exercises.ExerciseDetails;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
public interface ExcerciseDetailsRepo extends JpaRepository<ExcerciseDetailsModel,Integer> {

    @Query("SELECT e FROM ExcerciseDetailsModel e WHERE e.trainer_id = :trainerId")
    List<ExcerciseDetailsModel> findByTrainerId(@Param("trainerId") Integer trainerId);

    @Query("SELECT e FROM ExcerciseDetailsModel e WHERE e.trainer_id = :trainerId And e.exercise_id =:exerciseId")
    Optional<ExcerciseDetailsModel> findByTrainerIdAndExercise_Id(Integer trainerId, Integer exerciseId);
}
