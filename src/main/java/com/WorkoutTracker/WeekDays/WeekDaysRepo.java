package com.WorkoutTracker.WeekDays;

import jakarta.persistence.Table;
import lombok.Data;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WeekDaysRepo extends JpaRepository<WeekDaysModel,Integer> {
}
