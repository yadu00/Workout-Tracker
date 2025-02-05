package com.WorkoutTracker.Admin;

import com.WorkoutTracker.Dto.TrainerDto;
import com.WorkoutTracker.Dto.UserDto;
import com.WorkoutTracker.Excercises.Specialization.ExcerciseSpecialisationModel;
import com.WorkoutTracker.Excercises.Specialization.ExcerciseSpecializationRepo;
import com.WorkoutTracker.Trainer.TrainerModel;
import com.WorkoutTracker.User.Registration.UserModel;
import com.WorkoutTracker.Trainer.TrainerRepo;
import com.WorkoutTracker.User.Registration.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service

public class AdminService {
    @Autowired
    private AdminRepo adminRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private TrainerRepo trainerRepo;

    @Autowired
    private ExcerciseSpecializationRepo excerciseSpecializationRepo;



    //add admin
    public ResponseEntity<?> addDetails(AdminModel adminModel) {
        AdminModel adminModel1 =new AdminModel();
        adminModel1.setUsername(adminModel.getUsername());
        adminModel1.setPassword(adminModel.getPassword());
        adminModel1.setCreated_date(LocalDate.now());
        adminRepo.save(adminModel1);
        return new ResponseEntity<>(adminModel1, HttpStatus.OK);
    }



    //login for admin
    public ResponseEntity<?> loginDetails(String username, String password) {
        Optional<AdminModel> adminModelOptional = adminRepo.findByUsernameAndPassword(username,password);
        if (adminModelOptional.isPresent()){
            return new ResponseEntity<>("Logged in as Admin", HttpStatus.OK);
        }else {
            return new ResponseEntity<>(" Admin Credentials Incorrect",HttpStatus.NOT_FOUND);
        }
    }



    //list all Users
    public ResponseEntity<List<UserDto>> getallUsers() {
        List<UserDto> dto = new ArrayList<>();
        List<UserModel> userModels = userRepo.findAll();
        if (!userModels.isEmpty()) {
            for (UserModel userModel : userModels) {
                UserDto userDto1 = new UserDto(userModel.getName(), userModel.getEmail());
                userDto1.setUser_id(userModel.getUser_id());
                userDto1.setName(userModel.getName());
                userDto1.setEmail(userModel.getEmail());
                dto.add(userDto1);
            }

        }

        return new ResponseEntity<>(dto, HttpStatus.OK);
    }




    //list all trainers
    public ResponseEntity<List<TrainerDto>> getallTrainers() {
        List<TrainerDto> dto = new ArrayList<>();
        List<TrainerModel> trainerModels = trainerRepo.findAll();
        if (!trainerModels.isEmpty()) {
            for (TrainerModel trainerModel : trainerModels) {
                TrainerDto trainerDto1 = new TrainerDto();
                trainerDto1.setTrainer_id(trainerModel.getTrainer_id());
                trainerDto1.setName(trainerModel.getName());
                trainerDto1.setEmail(trainerModel.getEmail());
                trainerDto1.setCertification(trainerModel.getCertification());
                trainerDto1.setExperienceYears(trainerModel.getExperienceYears());
                dto.add(trainerDto1);
            }

        }

        return new ResponseEntity<>(dto, HttpStatus.OK);
    }


    public ResponseEntity<?> addSpecialization(ExcerciseSpecialisationModel excerciseSpecialisationModel) {
        ExcerciseSpecialisationModel excerciseSpecialisationModel1=new ExcerciseSpecialisationModel();
        excerciseSpecialisationModel1.setSpecialization_name(excerciseSpecialisationModel.getSpecialization_name());
        excerciseSpecializationRepo.save(excerciseSpecialisationModel1);
        return new ResponseEntity<>(excerciseSpecialisationModel1,HttpStatus.OK);
    }
}
