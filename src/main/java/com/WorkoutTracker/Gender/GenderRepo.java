package com.WorkoutTracker.Gender;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GenderRepo extends JpaRepository<GenderModel,Integer> {


}
