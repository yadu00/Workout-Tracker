package com.WorkoutTracker.User.UserDetails;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://localhost:8081")

public interface UserDetailsRepo extends JpaRepository<UserDetails,Integer> {
}
