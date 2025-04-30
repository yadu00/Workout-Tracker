package com.WorkoutTracker.Workouts;

import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkoutStatusRepo extends JpaRepository<WorkoutStatusModel,Integer> {
}
