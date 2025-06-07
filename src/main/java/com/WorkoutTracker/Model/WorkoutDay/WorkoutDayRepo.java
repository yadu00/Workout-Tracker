package com.WorkoutTracker.Model.WorkoutDay;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface WorkoutDayRepo extends JpaRepository<WorkoutDayModal,Integer> {



    @Query("SELECT COUNT(d) FROM WorkoutDayModal d WHERE d.user_id = :userId AND d.trainer_id = :trainerId")
    int countByUser_IdAndTrainer_Id(Integer userId, Integer trainerId);

    @Query("SELECT ut FROM WorkoutDayModal ut WHERE ut.user_id = :userId")
    List<WorkoutDayModal> findByUser_Id(Integer userId);

    @Query("SELECT ut FROM WorkoutDayModal ut WHERE ut.user_id = :userId And ut.date =:date")
    Optional<WorkoutDayModal> findByUser_IdAndDate(Integer userId, LocalDate date);



    @Query("SELECT ut FROM WorkoutDayModal ut WHERE ut.user_id = :userId And ut.date =:date")
    List<WorkoutDayModal> findByUser_IdDate(Integer userId, LocalDate date);
}
