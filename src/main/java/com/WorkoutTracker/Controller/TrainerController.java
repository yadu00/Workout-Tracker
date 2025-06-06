package com.WorkoutTracker.Controller;

import com.WorkoutTracker.Dto.*;
import com.WorkoutTracker.Model.DailyWorkout.DailyWorkout;
import com.WorkoutTracker.Model.Exercises.ExerciseDetails.ExcerciseDetailsModel;
import com.WorkoutTracker.Model.Exercises.Specialization.ExcerciseSpecialisationModel;
import com.WorkoutTracker.Model.Trainer.TrainerModel;
import com.WorkoutTracker.Service.TrainerService;
import com.WorkoutTracker.Model.Workouts.WorkoutModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping(path = "/api/trainer")
public class TrainerController {


    @Autowired
    private TrainerService trainerService;



    //add trainer
    @PostMapping(path="/add-trainer")
    public ResponseEntity<?> addDetails(@RequestPart TrainerModel trainerModel, @RequestPart MultipartFile cert){
        try {
            return trainerService.addDetails(trainerModel,cert);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>("Something Went Wrong", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    //login for trainers
    @PostMapping(path = "/login-trainer")
    public ResponseEntity<?> loginDetails(@RequestBody LoginDto loginDto){
        try {
            return trainerService.loginDetails(loginDto);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>("Something Went Wrong", HttpStatus.INTERNAL_SERVER_ERROR);
    }



    //Add Exercises
    @PostMapping(path="/addExcercises")
    public ResponseEntity<?> addExcercises(@RequestBody ExcerciseDetailsModel excerciseDetailsModel){
        try {
            return trainerService.addExcercises(excerciseDetailsModel);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>("Something Went Wrong", HttpStatus.INTERNAL_SERVER_ERROR);
    }



    //view exercises
    @GetMapping(path = "/viewExercises")
    public ResponseEntity<List<ExcerciseDto>> viewExercises(@RequestParam Integer trainer_id) {

            return trainerService.getExercises(trainer_id);

    }

    //view clients
    @GetMapping(path = "/viewClients")
    public ResponseEntity<?> viewAssignedUsers(@RequestParam Integer trainer_id) {
        try {
            return trainerService.getAssignedUsers(trainer_id);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Something Went Wrong", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



    //add achievements
    @PostMapping(path="/add-achievements")
    public ResponseEntity<?> addAchievements(@RequestParam Integer trainer_id,@RequestParam String achievements){
        try {
            return trainerService.addAchievements(trainer_id,achievements);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>("Something Went Wrong", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    //reset trainer password
    @PutMapping(path = "/updateTrainerPassword")
    public ResponseEntity<?>updatePassword(@RequestParam String email,@RequestParam String password){
        try {
            return trainerService.updatePassword(email,password);
        }catch (Exception e){
            e.printStackTrace();
        }return new ResponseEntity<>("Something Went Wrong", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    //create workouts
    @PostMapping("/create-workout")
    public ResponseEntity<?> createWorkouts(@RequestBody WorkoutModel workoutModel) {
        try {
            return trainerService.createWorkouts(workoutModel);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Something Went Wrong", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    //schedule weekly workouts
    @PostMapping("/schedule-weekly-workout")
    public ResponseEntity<?> weeklyworkout(@RequestBody DailyWorkout dailyWorkout) {
        try {
            return trainerService.weeklyworkout(dailyWorkout);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Something Went Wrong", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    //view scheduled weekly workouts
    @GetMapping("/getWeeklyWorkouts")
    public ResponseEntity<?> daylyWorkouts(@RequestParam Integer user_id) {
        try {
            return trainerService.daylyWorkouts(user_id);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Something Went Wrong", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


   //view scheduled exercises on each day
    @GetMapping("/viewWorkout")
    public ResponseEntity<?> viewWorkout(@RequestParam Integer user_id,@RequestParam Integer id) {
        try{
            return trainerService.viewWorkout(user_id,id);
        }catch (Exception e){
            e.printStackTrace();
        }return new ResponseEntity<>("Something Went Wrong", HttpStatus.INTERNAL_SERVER_ERROR);
    }




    //Delete workout scheduled by trainer
    @DeleteMapping("/DeleteWorkouts")
    public ResponseEntity<?>DeleteWorkouts(@RequestParam Integer workout_id,@RequestParam Integer trainer_id){
        try {
            return trainerService.DeleteWorkouts(workout_id,trainer_id);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Something Went Wrong", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    //View Profile
    @GetMapping(path = "/ViewProfile")
    public ResponseEntity<?> ViewProfile(@RequestParam Integer trainer_id){
        try {
            return trainerService.ViewProfile(trainer_id);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>("Something Went Wrong", HttpStatus.INTERNAL_SERVER_ERROR);
    }


    //Edit Profile trainer
    @PutMapping(path = "/EditProfile")
    public ResponseEntity<?> EditProfile(@RequestParam Integer trainer_id, @RequestBody TrainerModel trainerModel){
        try {
            return trainerService.EditProfile(trainer_id,trainerModel);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>("Something Went Wrong", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    //Delete Account trainer
    @DeleteMapping (path = "/DeleteProfile")
    public ResponseEntity<?> DeleteProfile(@RequestParam Integer trainer_id){
        try {
            return trainerService.DeleteProfile(trainer_id);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>("Something Went Wrong", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    //view specialization
    @GetMapping(path = "/viewSpecialization")
    public ResponseEntity<List<ExcerciseSpecialisationModel>> viewSpecialisation() {

        return trainerService.viewSpecialisation();

    }


    //create about
    @PutMapping("/createabout")
    public ResponseEntity<?> description(@RequestParam Integer trainer_id,@RequestParam String about) {
        try {
            return trainerService.description(trainer_id,about);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Something Went Wrong", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    //view about
    @GetMapping("/viewabout")
    public ResponseEntity<?> showdescription(@RequestParam Integer trainer_id) {
        try {
            return trainerService.showdescription(trainer_id);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Something Went Wrong", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //delete exercise
    @DeleteMapping("/deleteExercise")
    public ResponseEntity<?> deleteExercise(@RequestParam Integer trainer_id,@RequestParam Integer exercise_id) {
        try {
            return trainerService.deleteExercise(trainer_id,exercise_id);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Something Went Wrong", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //view unscheduled clients
    @GetMapping("/unscheduled-users")
    public ResponseEntity<List<UserInfoDto>> getUnscheduledUsers(@RequestParam Integer trainerId) {
        return ResponseEntity.ok(trainerService.getUnscheduledUsers(trainerId));
    }


    //view clients payment status
    @GetMapping("/payment/status")
    public ResponseEntity<List<UserDto>> getPaymentStatus(@RequestParam Integer trainer_id) {
        return ResponseEntity.ok(trainerService.getPaymentStatus(trainer_id));
    }



}
