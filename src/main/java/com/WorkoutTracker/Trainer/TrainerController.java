package com.WorkoutTracker.Trainer;

import com.WorkoutTracker.Dto.ExcerciseDto;
import com.WorkoutTracker.Dto.LoginDto;
import com.WorkoutTracker.Dto.WeeKDayDto;
import com.WorkoutTracker.Dto.WorkoutUpdateDto;
import com.WorkoutTracker.Exercises.ExerciseDetails.ExcerciseDetailsModel;
import com.WorkoutTracker.Exercises.Specialization.ExcerciseSpecialisationModel;
import com.WorkoutTracker.WeekDays.WeekDaysModel;
import com.WorkoutTracker.Workouts.WorkoutModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping(path = "/api/trainer")
public class TrainerController {


    @Autowired
    private TrainerService trainerService;



    //add trainer
    @PostMapping(path="/add-trainer")
    public ResponseEntity<?> addDetails(@RequestBody TrainerModel trainerModel){
        try {
            return trainerService.addDetails(trainerModel);
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



    //Trainer Add Exercises
    @PostMapping(path="/addExcercises")
    public ResponseEntity<?> addExcercises(@RequestBody ExcerciseDetailsModel excerciseDetailsModel){
        try {
            return trainerService.addExcercises(excerciseDetailsModel);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>("Something Went Wrong", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    //trainer view exercises
    @GetMapping(path = "/viewExercises")
    public ResponseEntity<List<ExcerciseDto>> viewExercises(@RequestParam Integer trainer_id) {

            return trainerService.getExercises(trainer_id);

    }

    //trainer view Assigned Users
    @GetMapping(path = "/viewAssignedUsers")
    public ResponseEntity<?> viewAssignedUsers(@RequestParam Integer trainer_id) {
        try {
            return trainerService.getAssignedUsers(trainer_id);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Something Went Wrong", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //trainer select assigned users
    @PostMapping(path = "/selectUser")
    public ResponseEntity<?> selectUser(@RequestParam Integer trainer_id, @RequestParam Integer user_id) {
        try {
            return trainerService.selectUser(trainer_id,user_id);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Something Went Wrong", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



    //trainer add achievements
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

    //trainer schedule workouts for assigned users
    @PostMapping("/schedule-workouts")
    public ResponseEntity<?> scheduleWorkouts(@RequestBody WorkoutModel workoutModel) {
        try {
            return trainerService.scheduleWorkouts(workoutModel);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Something Went Wrong", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    //trainer schedule workouts for assigned users
    @PostMapping("/addweekdays")
    public ResponseEntity<?> addweekdays(@RequestBody List<WeekDaysModel> weekDaysModel) {
        try {
            return trainerService.addWeekdays(weekDaysModel);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Something Went Wrong", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/getweekday")
    public ResponseEntity<?> getWeekDay(@RequestParam Integer user_id) {
        try {
            return trainerService.getWeekDay(user_id);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Something Went Wrong", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/getweekdays")
    public ResponseEntity<?> getWeekDays(@RequestParam Integer user_id, @RequestParam String week) {
        try {
            return trainerService.getWeekDays(user_id,week);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Something Went Wrong", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

   //get scheduled workout on days
    @GetMapping("/viewWorkout")
    public ResponseEntity<?> viewWorkout(@RequestParam Integer user_id,@RequestParam Integer weekdayId) {
        try{
            return trainerService.viewWorkout(user_id,weekdayId);
        }catch (Exception e){
            e.printStackTrace();
        }return new ResponseEntity<>("Something Went Wrong", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    //trainer update scheduled workouts of user
    @PutMapping("/update-scheduled-workouts")
    public ResponseEntity<?> UpdateWorkouts(@RequestParam Integer workout_id, @RequestParam Integer trainer_id, @RequestParam Integer user_id, @RequestBody WorkoutUpdateDto workoutUpdateDto) {
        try {
            return trainerService.UpdateWorkouts(workout_id,trainer_id,user_id,workoutUpdateDto);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Something Went Wrong", HttpStatus.INTERNAL_SERVER_ERROR);
        }
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


    //View Profile trainer
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

    //trainer select specialisation
    @GetMapping(path = "/viewSpecialisation")
    public ResponseEntity<List<ExcerciseSpecialisationModel>> viewSpecialisation() {

        return trainerService.viewSpecialisation();

    }


    //trainer add description
    @PutMapping("/description")
    public ResponseEntity<?> description(@RequestParam Integer trainer_id,@RequestParam String about) {
        try {
            return trainerService.description(trainer_id,about);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Something Went Wrong", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    //trainer show description
    @GetMapping("/showdescription")
    public ResponseEntity<?> showdescription(@RequestParam Integer trainer_id) {
        try {
            return trainerService.showdescription(trainer_id);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Something Went Wrong", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    //trainer add description
    @PutMapping("/specialities")
    public ResponseEntity<?> specialities(@RequestParam  Integer trainer_id,@RequestParam  String specialities) {
        try {
            return trainerService.specialities(trainer_id,specialities);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Something Went Wrong", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
