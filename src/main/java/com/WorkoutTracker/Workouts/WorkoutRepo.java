package com.WorkoutTracker.Workouts;

import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkoutRepo extends JpaRepository<WorkoutModel,Integer> {
}
