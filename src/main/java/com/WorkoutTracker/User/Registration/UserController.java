package com.WorkoutTracker.User.Registration;

import com.WorkoutTracker.Dto.ExcerciseDto;
import com.WorkoutTracker.Dto.LoginDto;
import com.WorkoutTracker.Dto.TrainerDto;
import com.WorkoutTracker.Exercises.Specialization.ExcerciseSpecialisationModel;
import com.WorkoutTracker.User.UserDetails.UserDetails;
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


    //select specializations
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



    //get workouts days
//    @GetMapping("/days/{user_id}")
//    public ResponseEntity<?> getAllWorkoutDays(@PathVariable Integer user_id) {
//        try {
//             return userService.getAllWorkoutDays(user_id);
//        }catch (Exception e){
//            e.printStackTrace();
//        }return new ResponseEntity<>("Something Went Wrong", HttpStatus.INTERNAL_SERVER_ERROR);
//    }



    //get scheduled workout on days
    //get scheduled workout on days
//    @GetMapping("/viewWorkout")
//    public ResponseEntity<?> viewWorkout(@RequestParam Integer user_id,@RequestParam LocalDate workout,@RequestParam Integer trainer_id) {
//        try{
//            return userService.viewWorkout(user_id,workout,trainer_id);
//        }catch (Exception e){
//            e.printStackTrace();
//        }return new ResponseEntity<>("Something Went Wrong", HttpStatus.INTERNAL_SERVER_ERROR);
//    }

    //view trainer
    @GetMapping("/view/Trainer")
    public ResponseEntity<?> viewtrainer(@RequestParam Integer user_id,@RequestParam Integer trainer_id){
        try{
            return userService.viewTrainer(user_id,trainer_id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>("Something Went Wrong", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    //get scheduled workout on days
    @GetMapping("/viewWorkouts")
    public ResponseEntity<?> viewWorkouts(@RequestParam Integer user_id,@RequestParam Integer weekdayId) {
        try{
            return userService.viewWorkouts(user_id,weekdayId);
        }catch (Exception e){
            e.printStackTrace();
        }return new ResponseEntity<>("Something Went Wrong", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping("/getweekdays")
    public ResponseEntity<?> getWeekDays(@RequestParam Integer user_id) {
        try {
            return userService.getWeekDays(user_id);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Something Went Wrong", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/ViewAssignedTrainer")
    public ResponseEntity<?> ViewAssignedTrainer(@RequestParam Integer user_id) {
        try {
            return userService.ViewAssignedTrainer(user_id);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Something Went Wrong", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/UpdateWorkoutStatus")
    public ResponseEntity<?> UpdateWorkoutStatus(@RequestParam Integer user_id,@RequestParam Integer workout_id) {
        try {
            return userService.UpdateWorkoutStatus(user_id,workout_id);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Something Went Wrong", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/totalworkoutsdone")
    public ResponseEntity<?> totalworkoutsdone(@RequestParam Integer user_id) {
        try {
            return userService.totalworkoutsdone(user_id);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Something Went Wrong", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //get scheduled workout on today
    @GetMapping("/viewtodaysworkouts")
    public ResponseEntity<?> viewtodaysworkouts(@RequestParam Integer user_id,@RequestParam LocalDate workoutdate) {
        try{
            return userService.viewtodaysworkouts(user_id,workoutdate);
        }catch (Exception e){
            e.printStackTrace();
        }return new ResponseEntity<>("Something Went Wrong", HttpStatus.INTERNAL_SERVER_ERROR);
    }





}
