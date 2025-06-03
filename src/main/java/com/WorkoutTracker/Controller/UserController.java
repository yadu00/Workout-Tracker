package com.WorkoutTracker.Controller;

import com.WorkoutTracker.Dto.*;
import com.WorkoutTracker.Models.Exercises.Specialization.ExcerciseSpecialisationModel;
import com.WorkoutTracker.Models.User.UserModel;
import com.WorkoutTracker.Services.UserService;
import com.WorkoutTracker.Models.Bmi.BmiModel;
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
    public ResponseEntity<List<ExcerciseSpecialisationModel>> listSpecialization() {
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
    @PostMapping(path = "/assign")
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




    //user update payment details
    @PostMapping(path = "/updateRegPayment")
    public ResponseEntity<?> updateRegPayment(@RequestParam Integer user_id,Boolean paymentStatus){
        try {
            return userService.updatePayment(user_id,paymentStatus);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>("Something Went Wrong", HttpStatus.INTERNAL_SERVER_ERROR);
    }


    //add Bmi details
    @PostMapping(path="/addBmi")
    public ResponseEntity<?> addBmi(@RequestParam double height,@RequestParam double weight,@RequestParam Integer user_id ){
        try {
            return userService.addBmi(height,weight,user_id);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>("Something Went Wrong", HttpStatus.INTERNAL_SERVER_ERROR);
    }


    //Update bmi details
    @PutMapping(path = "/updateBmi")
    public ResponseEntity<?> updateBmi(@RequestParam Integer user_id,@RequestParam double height,@RequestParam double weight) {
        try {
            return userService.updateBmi(user_id, height, weight);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>("Something Went Wrong", HttpStatus.INTERNAL_SERVER_ERROR);

    }

    //view Bmi
    @GetMapping("/viewBmi")
    public ResponseEntity<?> viewBmi(@RequestParam Integer user_id) {
        try {
            return userService.viewBmi(user_id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>("Something Went Wrong", HttpStatus.INTERNAL_SERVER_ERROR);

    }


    //user view trainer excercises
    @GetMapping(path = "/viewTrainer-excercises")
    public ResponseEntity<List<ExcerciseDto>> viewExcercises(@RequestParam Integer trainer_id){
        return userService.viewExcercises(trainer_id);
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


    //view scheduled exercise on days
    @GetMapping("/viewWorkouts")
    public ResponseEntity<?> viewWorkouts(@RequestParam Integer user_id,@RequestParam Integer id) {
        try{
            return userService.viewWorkouts(user_id,id);
        }catch (Exception e){
            e.printStackTrace();
        }return new ResponseEntity<>("Something Went Wrong", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    //view weekly workouts
    @GetMapping("/weeklyworkout")
    public ResponseEntity<?> getWeekDays(@RequestParam Integer user_id) {
        try {
            return userService.getWeekDays(user_id);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Something Went Wrong", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //view scheduled weekly workouts
    @GetMapping("/getdaylyWorkouts")
    public ResponseEntity<?> daylyWorkouts(@RequestParam Integer user_id) {
        try {
            return userService.daylyWorkouts(user_id);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Something Went Wrong", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    //view scheduled weekly workouts
    @GetMapping("/gettodaysWorkout")
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


    //update status of workouts
    @PutMapping("/UpdateWorkoutStatus")
    public ResponseEntity<?> UpdateWorkoutStatus(@RequestParam Integer user_id,@RequestParam Integer workout_id) {
        try {
            return userService.UpdateWorkoutStatus(user_id,workout_id);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Something Went Wrong", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //log status of workouts
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
    @GetMapping("/viewtodaysworkouts")
    public ResponseEntity<?> viewtodaysworkouts(@RequestParam Integer user_id,@RequestParam LocalDate date) {
        try{
            return userService.viewtodaysworkouts(user_id,date);
        }catch (Exception e){
            e.printStackTrace();
        }return new ResponseEntity<>("Something Went Wrong", HttpStatus.INTERNAL_SERVER_ERROR);
    }


    //log weight details
    @PostMapping(path="/logBmi")
    public ResponseEntity<?> logBmi(@RequestBody BmiModel bmiModel, @RequestParam Integer user_id){
        try {
            return userService.logBmi(bmiModel,user_id);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>("Something Went Wrong", HttpStatus.INTERNAL_SERVER_ERROR);
    }


    //weight history
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


    //show trainer rating
    @GetMapping("/viewPaymentPlan")
    public ResponseEntity<?> viewPaymentPlan(@RequestParam Integer user_id) {
        try {
            return userService.viewPaymentPlan(user_id);
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


    //get subscription details
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
