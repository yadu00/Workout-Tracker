package com.WorkoutTracker.Trainer;

import com.WorkoutTracker.Excercises.ExcerciseDetails.ExcerciseDetailsModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/trainer")
public class TrainerController {
    @Autowired
    private TrainerService trainerService;
    //add trainer
    @PostMapping(path="/add")
    public ResponseEntity<?> addDetails(@RequestBody TrainerModel trainerModel){
        try {
            return trainerService.addDetails(trainerModel);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>("Something Went Wrong", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    //login for trainers
    @PostMapping(path = "/login")
    public ResponseEntity<?> loginDetails(@RequestParam String email, @RequestParam String password){
        try {
            return trainerService.loginDetails(email,password);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>("Something Went Wrong", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    //Add Excercises
    @PostMapping(path="/addExcercises")
    public ResponseEntity<?> addExcercises(@RequestBody ExcerciseDetailsModel excerciseDetailsModel){
        try {
            return trainerService.addExcercises(excerciseDetailsModel);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>("Something Went Wrong", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    //view Assigned Users
    @GetMapping(path = "/viewAssignedUsers")
    public ResponseEntity<?> viewAssignedUsers(@RequestParam Integer trainer_id) {
        try {
            return trainerService.getAssignedUsers(trainer_id);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Something Went Wrong", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //select assigned users
    @PostMapping(path = "/selectUser")
    public ResponseEntity<?> selectUser(@RequestParam Integer trainer_id, @RequestParam Integer user_id) {
        try {
            return trainerService.selectUser(trainer_id,user_id);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Something Went Wrong", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //schedule workouts
//    @PostMapping("/schedule-workouts")
//    public ResponseEntity<?> scheduleWorkouts(@RequestParam Integer userId, @RequestBody List<WorkoutDto> workouts) {
//      try {
//          return trainerService.scheduleWorkouts(userId, workouts);
//      } catch (Exception e) {
//          e.printStackTrace();
//          return new ResponseEntity<>("Something Went Wrong", HttpStatus.INTERNAL_SERVER_ERROR);
//      }
//    }

    //add achievements
    @PostMapping(path="/add-achievements")
    public ResponseEntity<?> addAchievements(@RequestParam Integer trainer_id){
        try {
            return trainerService.addAchievements(trainer_id);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>("Something Went Wrong", HttpStatus.INTERNAL_SERVER_ERROR);
    }


}
