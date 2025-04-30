package com.WorkoutTracker.Admin;

import com.WorkoutTracker.Dto.AdminLoginDto;
import com.WorkoutTracker.Dto.TrainerDto;
import com.WorkoutTracker.Dto.UserDto;
import com.WorkoutTracker.Exercises.ExerciseCategory.ExcerciseCategory;
import com.WorkoutTracker.Exercises.Specialization.ExcerciseSpecialisationModel;
import com.WorkoutTracker.Gender.GenderModel;
import com.WorkoutTracker.SignUpStatus.StatusModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping (path = "/api/admin")
public class AdminController {
    @Autowired
    private AdminService adminService;

    //add admin account
    @PostMapping(path="/add-admin")
    public ResponseEntity<?> addDetails(@RequestBody AdminModel adminModel){
        try {
            return adminService.addDetails(adminModel);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>("Something Went Wrong", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    //admin login
    @PostMapping(path = "/login-admin")
    public ResponseEntity<?> loginDetails(@RequestBody AdminLoginDto adminLoginDto){
        try {
            return adminService.loginDetails(adminLoginDto);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>("Something Went Wrong", HttpStatus.INTERNAL_SERVER_ERROR);
    }


    //list all users
    @GetMapping(path = "/viewUsers")
    public ResponseEntity<List<UserDto>> listusers() {

        return adminService.getallUsers();
    }

    //list all trainers
    @GetMapping(path = "/viewTrainers")
    public ResponseEntity<List<TrainerDto>> listtrainers() {

        return adminService.getallTrainers();
    }

    //delete user
    @DeleteMapping(path = "/delete-User")
    public ResponseEntity<?> deleteUser(@RequestParam Integer user_id){
        try{
            return adminService.deleteUser(user_id);
        } catch (Exception e) {
           e.printStackTrace();
        }
        return new ResponseEntity<>("SomeThing Went Wrong",HttpStatus.INTERNAL_SERVER_ERROR);
    }
    //delete trainer
    @DeleteMapping(path = "/delete-Trainer")
    public ResponseEntity<?> deleteTrainerser(@RequestParam Integer trainer_id){
        try{
            return adminService.deleteTrainer(trainer_id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>("SomeThing Went Wrong",HttpStatus.INTERNAL_SERVER_ERROR);
    }


    //add exercise Specializations
    @PostMapping(path="/addSpecialization")
    public ResponseEntity<?> addSpecialization(@RequestBody ExcerciseSpecialisationModel excerciseSpecialisationModel){
        try {
            return adminService.addSpecialization(excerciseSpecialisationModel);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>("Something Went Wrong", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    //list specializations
    @GetMapping(path = "/viewspecialisations")
    public ResponseEntity<List<ExcerciseSpecialisationModel>> specialisations() {

        return adminService.specialisations();
    }


    //add gender information
    @PostMapping(path = "/addGender")
    public ResponseEntity<?> addGender(@RequestBody GenderModel genderModel){
        try {
            return adminService.addGender(genderModel);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>("Something Went Wrong", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    //list genders
    @GetMapping(path = "/viewgenders")
    public ResponseEntity<List<GenderModel>> genders() {

        return adminService.genders();
    }




    //Add exercise Category
    @PostMapping(path="/addExcerciseCategory")
    public ResponseEntity<?> addExcerciseCategory(@RequestBody ExcerciseCategory excerciseCategory){
        try {
            return adminService.addExcerciseCategory(excerciseCategory);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>("Something Went Wrong", HttpStatus.INTERNAL_SERVER_ERROR);
    }

//    //add week days
//    @PostMapping(path="/addWeekDays")
//    public ResponseEntity<?> addWeekDays(@RequestBody WeekDaysModel weekDaysModel){
//        try {
//            return adminService.addWeekDays(weekDaysModel);
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//        return new ResponseEntity<>("Something Went Wrong", HttpStatus.INTERNAL_SERVER_ERROR);
//    }
    //Add Registration Status
    @PostMapping(path = "/AddRegStatus")
    public ResponseEntity<?> addStatus(@RequestBody StatusModel statusModel){
        try {
            return adminService.addStatus(statusModel);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>("Something Went Wrong", HttpStatus.INTERNAL_SERVER_ERROR);
    }
    //Approve Registration
    @PutMapping(path = "/ApproveTrainer")
    public ResponseEntity<?> ApproveTrainer(@RequestParam Integer trainer_id,@RequestParam Integer statusId){
        try {
            return adminService.ApproveTrainer(trainer_id,statusId);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>("Something Went Wrong", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    //view trainer signup Requests
    @GetMapping(path = "/viewRequests")
    public ResponseEntity<List<TrainerDto>> listrequests() {

        return adminService.listrequests();
    }
    //view trainer details
    @GetMapping(path = "/TrainerDetails")
    public ResponseEntity<?> viewtrainer(@RequestParam Integer trainer_id) {

        return adminService.viewtrainer(trainer_id);
    }


}
