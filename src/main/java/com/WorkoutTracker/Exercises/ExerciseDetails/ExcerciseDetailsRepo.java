package com.WorkoutTracker.Exercises.ExerciseDetails;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
public interface ExcerciseDetailsRepo extends JpaRepository<ExcerciseDetailsModel,Integer> {

    @Query("SELECT e FROM ExcerciseDetailsModel e WHERE e.trainer_id = :trainerId")
    List<ExcerciseDetailsModel> findByTrainerId(@Param("trainerId") Integer trainerId);

}
