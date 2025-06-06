package com.WorkoutTracker.Model.User;

import com.WorkoutTracker.Dto.UserInfoDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface UserRepo extends JpaRepository<UserModel,Integer> {
    Optional<UserModel> findByEmailAndPassword(String email, String password);

    Optional<UserModel> findByEmail(String email);

//    @Query("SELECT u FROM UserModel u WHERE u.user_id = :userId")
//    List<UserModel> findByUserId(Integer userId);

    @Query(value = """
    SELECT new com.WorkoutTracker.Dto.UserInfoDto(u.user_id, u.name)
    FROM UserModel u
    JOIN UserTrainerModel ut ON u.user_id = ut.user_id
    WHERE ut.trainer_id = :trainerId
      AND u.user_id NOT IN (
          SELECT dw.user_id
          FROM DailyWorkout dw
          WHERE dw.date = :today
            AND dw.trainer_id = :trainerId
      )
""")
    List<UserInfoDto> findUsersWithoutWorkoutToday(@Param("trainerId") Integer trainerId,
                                               @Param("today") LocalDate today);


}
