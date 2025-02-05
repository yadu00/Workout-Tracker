package com.WorkoutTracker.User.Registration;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepo extends JpaRepository<UserModel,Integer> {
    Optional<UserModel> findByEmailAndPassword(String email, String password);

    Optional<UserModel> findByEmail(String email);

}
