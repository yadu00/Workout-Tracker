package com.WorkoutTracker.Models.WeekDays;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface WeekDaysRepo extends JpaRepository<WeekDaysModel,Integer> {
//    @Query("SELECT ut FROM WeekDaysModel ut WHERE ut.user_id = :userId And ut.week = :week And ut.day = :day")
//    List<WeekDaysModel> findByUser_IdAndWeekAndDay(Integer userId, String week, String day);

    @Query("SELECT ut FROM WeekDaysModel ut WHERE ut.user_id = :userId And ut.week = :week")
    List<WeekDaysModel> findByUser_IdAndWeek(Integer userId, String week);

    @Query("SELECT ut FROM WeekDaysModel ut WHERE ut.user_id = :userId")
    List<WeekDaysModel> findByUser_Id(Integer userId);



}
