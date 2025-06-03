package com.WorkoutTracker.Models.Bmi;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface BmiRepo extends JpaRepository<BmiModel,Integer> {



    @Query("SELECT w FROM BmiModel w WHERE w.user_id = :userId")
    List<BmiModel> findAllByUser_Id(Integer userId);



    @Query("SELECT b FROM BmiModel b WHERE b.user_id = :userId ORDER BY b.logDate DESC LIMIT 1")
    Optional<BmiModel> findLatestBmiByUserId(@Param("userId") Integer userId);

}
