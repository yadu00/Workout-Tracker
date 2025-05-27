package com.WorkoutTracker.WeightLog;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface WeightRepo extends JpaRepository<WeightModel,Integer> {



    @Query("SELECT w FROM WeightModel w WHERE w.user_id = :userId")
    List<WeightModel> findAllByUser_Id(Integer userId);
}
