package com.WorkoutTracker.Reports;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.Optional;

@CrossOrigin(origins = "http://localhost:8081")



public interface UserDetailsRepo extends JpaRepository<UserDetails,Integer> {
    @Query("SELECT ut FROM UserDetails ut WHERE ut.user_id = :userId")
    Optional<UserDetails> findByUser_Id(Integer userId);

}
