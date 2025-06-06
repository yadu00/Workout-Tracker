package com.WorkoutTracker.Model.Admin;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface AdminRepo extends JpaRepository<AdminModel,Integer> {
    Optional<AdminModel> findByUsernameAndPassword(String username, String password);

}
