package com.WorkoutTracker.Admin;

import com.WorkoutTracker.Dto.AdminLoginDto;
import com.WorkoutTracker.Dto.TrainerDto;
import com.WorkoutTracker.Dto.UserDto;
import com.WorkoutTracker.Exercises.ExerciseCategory.ExcerciseCategory;
import com.WorkoutTracker.Exercises.ExerciseCategory.ExcerciseCategoryRepo;
import com.WorkoutTracker.Exercises.Specialization.ExcerciseSpecialisationModel;
import com.WorkoutTracker.Exercises.Specialization.ExcerciseSpecializationRepo;
import com.WorkoutTracker.Gender.GenderModel;
import com.WorkoutTracker.Gender.GenderRepo;
import com.WorkoutTracker.SignUpStatus.StatusModel;
import com.WorkoutTracker.SignUpStatus.StatusRepo;
import com.WorkoutTracker.Trainer.TrainerModel;
import com.WorkoutTracker.User.Registration.UserModel;
import com.WorkoutTracker.Trainer.TrainerRepo;
import com.WorkoutTracker.User.Registration.UserRepo;
import com.WorkoutTracker.WeekDays.WeekDaysRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

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

    @Autowired
    private GenderRepo genderRepo;

    @Autowired
    private ExcerciseCategoryRepo excerciseCategoryRepo;

    @Autowired
    private WeekDaysRepo weekDaysRepo;

    @Autowired
    private StatusRepo statusRepo;


    //add admin account details
    public ResponseEntity<?> addDetails(AdminModel adminModel) {
        AdminModel adminModel1 =new AdminModel();
        adminModel1.setUsername(adminModel.getUsername());
        adminModel1.setPassword(adminModel.getPassword());
        adminModel1.setCreated_date(LocalDate.now());
        adminRepo.save(adminModel1);
        return new ResponseEntity<>(adminModel1, HttpStatus.OK);
    }



    //login for admin
    public ResponseEntity<?> loginDetails(AdminLoginDto adminLoginDto) {
        Optional<AdminModel> adminModelOptional = adminRepo.findByUsernameAndPassword(adminLoginDto.getUsername(), adminLoginDto.getPassword());
        if (adminModelOptional.isPresent()){
            Integer admin_id=adminModelOptional.get().getId();
            Map<String,Object> loginres=new HashMap<>();
            loginres.put("message","Login success");
            loginres.put("admin_id",admin_id);
            return new ResponseEntity<>(loginres, HttpStatus.OK);
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

    //add specialization for exercise
    public ResponseEntity<?> addSpecialization(ExcerciseSpecialisationModel excerciseSpecialisationModel) {
        ExcerciseSpecialisationModel excerciseSpecialisationModel1=new ExcerciseSpecialisationModel();
        excerciseSpecialisationModel1.setSpecialization_name(excerciseSpecialisationModel.getSpecialization_name());
        excerciseSpecializationRepo.save(excerciseSpecialisationModel1);
        return new ResponseEntity<>(excerciseSpecialisationModel1,HttpStatus.OK);
    }



    //add gender information
    public ResponseEntity<?> addGender(GenderModel genderModel) {
        GenderModel genderModel1 =new GenderModel();
        genderModel1.setGenderName(genderModel.getGenderName());
        genderRepo.save(genderModel1);
        return new ResponseEntity<>(genderModel1, HttpStatus.OK);
    }


    //add exercise categories
    public ResponseEntity<?> addExcerciseCategory(ExcerciseCategory excerciseCategory) {
        ExcerciseCategory excerciseCategory1=new ExcerciseCategory();
        excerciseCategory1.setCategory_name(excerciseCategory.getCategory_name());
        excerciseCategoryRepo.save(excerciseCategory1);
        return new ResponseEntity<>(excerciseCategory1,HttpStatus.OK);
    }

//    public ResponseEntity<?> addWeekDays(WeekDaysModel weekDaysModel) {
//        WeekDaysModel weekDaysModel1=new WeekDaysModel();
//        weekDaysModel1.setDay(weekDaysModel.getDay());
//        weekDaysRepo.save(weekDaysModel1);
//        return new ResponseEntity<>(weekDaysModel1,HttpStatus.OK);
//    }


    //add gender informations
    public ResponseEntity<List<GenderModel>> genders() {
            List<GenderModel> genderModels = genderRepo.findAll();

            if (genderModels.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(genderModels, HttpStatus.OK);
        }



    //list all exercise specializations
    public ResponseEntity<List<ExcerciseSpecialisationModel>> specialisations() {
        List<ExcerciseSpecialisationModel> excerciseSpecialisationModels = excerciseSpecializationRepo.findAll();
        if (excerciseSpecialisationModels.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(excerciseSpecialisationModels, HttpStatus.OK);
    }


    //delete user
    public ResponseEntity<?> deleteUser(Integer userId) {
        Optional<UserModel> userModelOptional=userRepo.findById(userId);
        if (userModelOptional.isPresent()){
            UserModel userModel=userModelOptional.get();
            userRepo.delete(userModel);
            return new ResponseEntity<>("User Deleted Successfully",HttpStatus.OK);
        }
        return new ResponseEntity<>("User Not Found",HttpStatus.NOT_FOUND);
    }

    //delete trainer
    public ResponseEntity<?> deleteTrainer(Integer trainerId) {
        Optional<TrainerModel> trainerModelOptional=trainerRepo.findById(trainerId);
        if (trainerModelOptional.isPresent()){
            TrainerModel trainerModel=trainerModelOptional.get();
            trainerRepo.delete(trainerModel);
            return new ResponseEntity<>("Trainer Deleted Successfully",HttpStatus.OK);
        }
        return new ResponseEntity<>("Trainer Not Found",HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<?> addStatus(StatusModel statusModel) {
        StatusModel statusModel1 =new StatusModel();
        statusModel1.setStatus(statusModel.getStatus());
        statusRepo.save(statusModel1);
        return new ResponseEntity<>(statusModel1, HttpStatus.OK);

    }

    public ResponseEntity<?> ApproveTrainer(Integer trainerId, Integer statusId) {
        Optional<TrainerModel> trainerModelOptional=trainerRepo.findById(trainerId);
        Optional<StatusModel> statusModelOptional=statusRepo.findById(statusId);
        if (statusModelOptional.isPresent()){
            if (trainerModelOptional.isPresent()) {
                TrainerModel trainerModel = trainerModelOptional.get();
                trainerModel.setStatusID(statusId);
                trainerRepo.save(trainerModel);
                return new ResponseEntity<>("Trainer status Approved",HttpStatus.OK);
            }else
                return new ResponseEntity<>("Trainer Not found",HttpStatus.NOT_FOUND);
        }else{
            return new ResponseEntity<>("Status Not found",HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<List<TrainerDto>> listrequests() {
        List<TrainerDto> dto = new ArrayList<>();
        List<TrainerModel> trainerModels = trainerRepo.findAll();
        if (!trainerModels.isEmpty()) {
            for (TrainerModel trainerModel : trainerModels) {
                if (!trainerModel.getStatusID().equals(1)) continue;
                TrainerDto trainerDto1 = new TrainerDto();
                trainerDto1.setTrainer_id(trainerModel.getTrainer_id());
                trainerDto1.setName(trainerModel.getName());
                Optional<StatusModel>statusModelOptional=statusRepo.findById(trainerModel.getStatusID());
                if (statusModelOptional.isPresent()){
                    StatusModel statusModel=statusModelOptional.get();
                    trainerDto1.setStatus(statusModel.getStatus());

                }

                dto.add(trainerDto1);
            }

        }

        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    public ResponseEntity<?> viewtrainer(Integer trainerId) {
        Optional<TrainerModel> trainerModelOptional = trainerRepo.findById(trainerId);
        if (trainerModelOptional.isPresent()) {
            TrainerModel trainerModel = trainerModelOptional.get();
            TrainerDto trainerDto = new TrainerDto();
            trainerDto.setTrainer_id(trainerModel.getTrainer_id());
            trainerDto.setName(trainerModel.getName());
            trainerDto.setEmail(trainerModel.getEmail());
            trainerDto.setPassword(trainerModel.getPassword());
            trainerDto.setCertification(trainerModel.getCertification());
            trainerDto.setExperienceYears(trainerModel.getExperienceYears());
            Optional<ExcerciseSpecialisationModel> excerciseSpecialisationModel = excerciseSpecializationRepo.findById(trainerModel.getSpecialization_id());
            if (excerciseSpecialisationModel.isPresent()) {
                trainerDto.setSpecialisationName(excerciseSpecialisationModel.get().getSpecialization_name());
            } else {
                trainerDto.setSpecialisationName("Not specified");
            }

            return new ResponseEntity<>(trainerDto, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Trainer not found", HttpStatus.NOT_FOUND);
        }
    }

}




