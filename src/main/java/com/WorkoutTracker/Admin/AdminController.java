package com.WorkoutTracker.Admin;

import com.WorkoutTracker.Dto.TrainerDto;
import com.WorkoutTracker.Dto.UserDto;
import com.WorkoutTracker.Excercises.Specialization.ExcerciseSpecialisationModel;
import com.WorkoutTracker.Excercises.Specialization.ExcerciseSpecializationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping (path = "/api/admin")
public class AdminController {
    @Autowired
    private AdminService adminService;

    //add admin
    @PostMapping(path="/add")
    public ResponseEntity<?> addDetails(@RequestBody AdminModel adminModel){
        try {
            return adminService.addDetails(adminModel);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>("Something Went Wrong", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    //login
    @PostMapping(path = "/login")
    public ResponseEntity<?> loginDetails(@RequestParam String username, @RequestParam String password){
        try {
            return adminService.loginDetails(username,password);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>("Something Went Wrong", HttpStatus.INTERNAL_SERVER_ERROR);
    }


    //list users
    @GetMapping(path = "/viewUsers")
    public ResponseEntity<List<UserDto>> listusers() {

        return adminService.getallUsers();
    }

    //list trainers
    @GetMapping(path = "/viewTrainers")
    public ResponseEntity<List<TrainerDto>> listtrainers() {

        return adminService.getallTrainers();
    }


    //add excercise Specializations
    @PostMapping(path="/addSpecialization")
    public ResponseEntity<?> addSpecialization(@RequestBody ExcerciseSpecialisationModel excerciseSpecialisationModel){
        try {
            return adminService.addSpecialization(excerciseSpecialisationModel);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>("Something Went Wrong", HttpStatus.INTERNAL_SERVER_ERROR);
    }



}
