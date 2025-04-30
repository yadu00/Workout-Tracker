package com.WorkoutTracker.SignUpStatus;

import org.springframework.data.jpa.repository.JpaRepository;

public interface StatusRepo extends JpaRepository<StatusModel,Integer> {
}
