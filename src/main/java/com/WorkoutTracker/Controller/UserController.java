package com.WorkoutTracker.Controller;

import com.WorkoutTracker.Dto.*;
import com.WorkoutTracker.Model.ExerciseSpecialization.ExcerciseSpecializationModel;
import com.WorkoutTracker.Model.User.UserModel;
import com.WorkoutTracker.Service.UserService;
import com.WorkoutTracker.Model.Bmi.BmiModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping(path = "/api/user")
public class UserController {
    @Autowired
    private UserService userService;


    //add user account
    @PostMapping(path="/add-user")
    public ResponseEntity<?> addDetails(@RequestBody UserModel userModel){
        try {
            return userService.addDetails(userModel);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>("Something Went Wrong", HttpStatus.INTERNAL_SERVER_ERROR);
    }


    //login user account
    @PostMapping(path = "/login-user")
    public ResponseEntity<?> loginDetails(@RequestBody LoginDto loginDto){
        try {
            return userService.loginDetails(loginDto);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>("Something Went Wrong", HttpStatus.INTERNAL_SERVER_ERROR);
    }


    //View Profile user
    @GetMapping(path = "/ViewProfile")
    public ResponseEntity<?> ViewProfile(@RequestParam Integer user_id){
        try {
            return userService.ViewProfile(user_id);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>("Something Went Wrong", HttpStatus.INTERNAL_SERVER_ERROR);
    }




    //Edit Profile user
    @PutMapping(path = "/EditProfile")
    public ResponseEntity<?> EditProfile(@RequestParam Integer user_id, @RequestBody UserModel userModel){
        try {
            return userService.EditProfile(user_id,userModel);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>("Something Went Wrong", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    //Delete Account user
    @DeleteMapping (path = "/DeleteProfile")
    public ResponseEntity<?> DeleteProfile(@RequestParam Integer user_id){
        try {
            return userService.DeleteProfile(user_id);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>("Something Went Wrong", HttpStatus.INTERNAL_SERVER_ERROR);
    }


    //view specializations
    @GetMapping(path = "/viewSpecializations")
    public ResponseEntity<List<ExcerciseSpecializationModel>> listSpecialization() {
        return userService.getAllSpecialization();
    }



    //list trainers
    @GetMapping(path = "/viewTrainers")
    public ResponseEntity<List<TrainerDto>> listtrainers() {

        return userService.getallTrainers();
    }



    //list trainers according to specialization
    @GetMapping(path = "/viewTrainers/specialization")
    public ResponseEntity<List<TrainerDto>> listtrainer(@RequestParam Integer specialization_id) {
        return userService.filterTrainers(specialization_id);
    }


    //assign trainer
    @PostMapping(path = "/assignTrainer")
    public ResponseEntity<?> assignTrainer(@RequestBody Map<String, Integer> requestBody) {
        try {
            Integer userId = requestBody.get("user_id");
            Integer trainerId = requestBody.get("trainer_id");

            if (userId == null || trainerId == null) {
                return new ResponseEntity<>("Missing user_id or trainer_id", HttpStatus.BAD_REQUEST);
            }

            return userService.assignTrainer(userId, trainerId);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Something Went Wrong", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }





    //update password
    @PutMapping(path = "/updateUserPassword")
    public ResponseEntity<?>updatePassword(@RequestParam String email,@RequestParam String password){
        try {
            return userService.updatePassword(email,password);
        }catch (Exception e){
            e.printStackTrace();
        }return new ResponseEntity<>("Something Went Wrong", HttpStatus.INTERNAL_SERVER_ERROR);
    }


    //view daily scheduled workout
    @GetMapping("/viewDailyWorkouts")
    public ResponseEntity<?> viewWorkouts(@RequestParam Integer user_id,@RequestParam Integer id) {
        try{
            return userService.viewWorkouts(user_id,id);
        }catch (Exception e){
            e.printStackTrace();
        }return new ResponseEntity<>("Something Went Wrong", HttpStatus.INTERNAL_SERVER_ERROR);
    }



    //view weekly scheduled workouts
    @GetMapping("/getWeeklyWorkouts")
    public ResponseEntity<?> daylyWorkouts(@RequestParam Integer user_id) {
        try {
            return userService.daylyWorkouts(user_id);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Something Went Wrong", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    //view today's weekly scheduled  workout
    @GetMapping("/todayWeeklyWorkout")
    public ResponseEntity<?> gettodaysWorkout(@RequestParam Integer user_id,@RequestParam LocalDate date) {
        try {
            return userService.gettodaysWorkout(user_id,date);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Something Went Wrong", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



    //view assigned trainer
    @GetMapping("/ViewAssignedTrainer")
    public ResponseEntity<?> ViewAssignedTrainer(@RequestParam Integer user_id) {
        try {
            return userService.ViewAssignedTrainer(user_id);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Something Went Wrong", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    //view trainer from trainers
    @GetMapping("/ViewTrainer")
    public ResponseEntity<?> ViewTrainer(@RequestParam Integer trainer_id) {
        try {
            return userService.ViewTrainer(trainer_id);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Something Went Wrong", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



    //Update status of workouts
    @PutMapping("/logWorkoutStatus")
    public ResponseEntity<?> logWorkoutStatus(@RequestParam Integer workout_id, @RequestBody WorkoutUpdateDto workoutUpdateDto) {
        try {
            return userService.logWorkoutStatus(workout_id,workoutUpdateDto);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Something Went Wrong", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    //view total workouts done
    @GetMapping("/totalworkoutsdone")
    public ResponseEntity<?> totalworkoutsdone(@RequestParam Integer user_id) {
        try {
            return userService.totalworkoutsdone(user_id);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Something Went Wrong", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    //view pending workouts done
    @GetMapping("/totalworkoutspending")
    public ResponseEntity<?> totalworkoutspending(@RequestParam Integer user_id) {
        try {
            return userService.totalworkoutspending(user_id);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Something Went Wrong", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //view todays workout
    @GetMapping("/viewTodaysExercise")
    public ResponseEntity<?> viewTodaysExercise(@RequestParam Integer user_id,@RequestParam LocalDate date) {
        try{
            return userService.viewTodaysExercise(user_id,date);
        }catch (Exception e){
            e.printStackTrace();
        }return new ResponseEntity<>("Something Went Wrong", HttpStatus.INTERNAL_SERVER_ERROR);
    }


    //log Bmi
    @PostMapping(path="/logBmi")
    public ResponseEntity<?> logBmi(@RequestBody BmiModel bmiModel, @RequestParam Integer user_id){
        try {
            return userService.logBmi(bmiModel,user_id);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>("Something Went Wrong", HttpStatus.INTERNAL_SERVER_ERROR);
    }


    //Bmi history
    @GetMapping("/BmiHistory")
    public ResponseEntity<?> BmiHistory(@RequestParam Integer user_id) {
        try {
            return userService.BmiHistory(user_id);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Something Went Wrong", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //add trainer rating
    @PostMapping(path="/addRating")
    public ResponseEntity<?> addRating(@RequestParam Integer rating , @RequestParam Integer user_id){
        try {
            return userService.addRating(rating,user_id);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>("Something Went Wrong", HttpStatus.INTERNAL_SERVER_ERROR);
    }


    //show trainer rating
    @GetMapping("/showRating")
    public ResponseEntity<?> showRating(@RequestParam Integer trainer_id) {
        try {
            return userService.showRating(trainer_id);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Something Went Wrong", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    //view current monthly plan
    @GetMapping("/viewPaymentPlan")
    public ResponseEntity<?> viewPaymentPlan(@RequestParam Integer user_id) {
        try {
            return userService.viewPaymentPlan(user_id);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Something Went Wrong", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //get payment details
    @GetMapping("/payment/details")
    public ResponseEntity<?> getPaymentDetails(@RequestParam Integer user_id) {
        try {
            return userService.getPaymentDetails(user_id);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Something Went Wrong", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    //get subscription details
    @GetMapping("/payment/status")
    public ResponseEntity<?> getPaymentStatus(@RequestParam Integer user_id) {
        try {
            return userService.getPaymentStatus(user_id);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Something Went Wrong", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    //get latest bmi
    @GetMapping("/latestBmi")
    public ResponseEntity<?> getLatestBmi(@RequestParam Integer user_id) {
        try {
            return userService.getLatestBmi(user_id);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Something Went Wrong", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
