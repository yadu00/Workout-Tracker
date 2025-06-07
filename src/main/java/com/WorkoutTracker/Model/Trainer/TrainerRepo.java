package com.WorkoutTracker.Model.Trainer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface TrainerRepo extends JpaRepository<TrainerModel,Integer> {
    Optional<TrainerModel> findByEmailAndPassword(String email, String password);

    Optional<TrainerModel> findByEmail(String email);





    List<TrainerModel> findByStatusID(int i);

    @Query("SELECT u FROM TrainerModel u WHERE u.specialization_id = :specializationId AND u.statusID = :statusID")
    List<TrainerModel> findBySpecializationIdAndStatusID(@Param("specializationId") Integer specializationId, @Param("statusID") Integer statusID);

}
