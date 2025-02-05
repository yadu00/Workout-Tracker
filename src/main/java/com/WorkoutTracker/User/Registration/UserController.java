package com.WorkoutTracker.User.Registration;

import com.WorkoutTracker.Dto.ExcerciseDto;
import com.WorkoutTracker.Dto.TrainerDto;
import com.WorkoutTracker.Excercises.Specialization.ExcerciseSpecialisationModel;
import com.WorkoutTracker.User.UserDetails.UserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/user")
public class UserController {
    @Autowired
    private UserService userService;


    //add user
    @PostMapping(path="/add")
    public ResponseEntity<?> addDetails(@RequestBody UserModel userModel){
        try {
            return userService.addDetails(userModel);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>("Something Went Wrong", HttpStatus.INTERNAL_SERVER_ERROR);
    }
    //login user
    @PostMapping(path = "/login")
    public ResponseEntity<?> loginDetails(@RequestParam String email, @RequestParam String password){
        try {
            return userService.loginDetails(email,password);
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


    //view trainers according to specialization
    @GetMapping(path = "/viewTrainers/specialization")
    public ResponseEntity<List<TrainerDto>> listtrainer(@RequestParam Integer specialization_id) {
        return userService.filterTrainers(specialization_id);
    }
    


    //Selecting a trainer
    @PostMapping(path = "/assignTrainer")
    public ResponseEntity<?> assignTrainer(@RequestParam Integer user_id, @RequestParam Integer trainer_id) {
        try {
            return userService.assignTrainer(user_id, trainer_id);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Something Went Wrong", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    //add Bmi details
    @PostMapping(path="/addBmi")
    public ResponseEntity<?> addBmi(@RequestBody UserDetails userDetails){
        try {
            return userService.addBmi(userDetails);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>("Something Went Wrong", HttpStatus.INTERNAL_SERVER_ERROR);
    }


    //Update bmi
    @PutMapping(path = "/updateBmi")
    public ResponseEntity<?> updateBmi(@RequestParam Integer user_id,@RequestParam double height,@RequestParam double weight) {
        try {
            return userService.updateBmi(user_id, height, weight);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>("Something Went Wrong", HttpStatus.INTERNAL_SERVER_ERROR);

    }

    //user view trainer excercises
    @GetMapping(path = "/viewTrainer-excercises")
    public ResponseEntity<List<ExcerciseDto>> viewExcercises(@RequestParam Integer trainer_id, @RequestParam Integer excercise_id) {
        return userService.viewExcercises(trainer_id,excercise_id);
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


}
