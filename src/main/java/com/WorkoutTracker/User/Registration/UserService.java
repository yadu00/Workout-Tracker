package com.WorkoutTracker.User.Registration;

import com.WorkoutTracker.Dto.ExcerciseDto;
import com.WorkoutTracker.Dto.TrainerDto;
import com.WorkoutTracker.Excercises.ExcerciseCategory.ExcerciseCategory;
import com.WorkoutTracker.Excercises.ExcerciseDetails.ExcerciseDetailsModel;
import com.WorkoutTracker.Excercises.ExcerciseDetails.ExcerciseDetailsRepo;
import com.WorkoutTracker.Excercises.Specialization.ExcerciseSpecialisationModel;
import com.WorkoutTracker.Excercises.Specialization.ExcerciseSpecializationRepo;
import com.WorkoutTracker.Trainer.TrainerModel;
import com.WorkoutTracker.User.UserDetails.UserDetails;
import com.WorkoutTracker.Trainer.TrainerRepo;
import com.WorkoutTracker.User.UserDetails.UserDetailsRepo;
import com.WorkoutTracker.UserTrainer.UserTrainerModel;
import com.WorkoutTracker.UserTrainer.UserTrainerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private TrainerRepo trainerRepo;

    @Autowired
    private UserDetailsRepo userDetailsRepo;

    @Autowired
    private ExcerciseSpecializationRepo excerciseSpecializationRepo;

    @Autowired
    private UserTrainerRepo userTrainerRepo;

    @Autowired
    private ExcerciseDetailsRepo excerciseDetailsRepo;

    @Autowired
    private ExcerciseCategory excerciseCategoryRepo;


    public ResponseEntity<?> addDetails(UserModel userModel) {
        UserModel userModel1 =new UserModel();
        userModel1.setName(userModel.getName());
        userModel1.setEmail(userModel.getEmail());
        userModel1.setPassword(userModel.getPassword());
        userModel1.setGender(userModel.getGender());
        userModel1.setCreated_date(LocalDate.now());
        userRepo.save(userModel1);
        return new ResponseEntity<>(userModel1, HttpStatus.OK);
    }

    public ResponseEntity<?> loginDetails(String email, String password) {
        Optional<UserModel> userModelOptional = userRepo.findByEmailAndPassword(email,password);
        if (userModelOptional.isPresent()){
            return new ResponseEntity<>("Login success", HttpStatus.OK);
        }else {
            return new ResponseEntity<>(" User Credentials Incorrect",HttpStatus.NOT_FOUND);
        }
    }


    public ResponseEntity<List<ExcerciseSpecialisationModel>> getAllSpecialization() {
        List<ExcerciseSpecialisationModel> excerciseSpecialisationModels = excerciseSpecializationRepo.findAll();
        if (!excerciseSpecialisationModels.isEmpty()) {
            return new ResponseEntity<>(excerciseSpecialisationModels, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }


    public ResponseEntity<List<TrainerDto>> filterTrainers(Integer specialization_id) {
        List<TrainerDto> dto = new ArrayList<>();
        List<TrainerModel> trainerModels = trainerRepo.findAll();
        if (!trainerModels.isEmpty()) {
            for (TrainerModel trainerModel : trainerModels) {
                Integer specialisation=trainerModel.getSpecialization_id();
                if (specialisation.equals(specialization_id)){
                    TrainerDto trainerDto1 = new TrainerDto();
                    trainerDto1.setName(trainerModel.getName());
                    trainerDto1.setCertification(trainerModel.getCertification());
                    trainerDto1.setSpecialization(trainerModel.getSpecialization_id());
                    trainerDto1.setExperienceYears(trainerModel.getExperienceYears());
                    dto.add(trainerDto1);
                }

            }

        }

        return new ResponseEntity<>(dto, HttpStatus.OK);

    }


    public ResponseEntity<?> addBmi(UserDetails userDetails) {
        double height=userDetails.getHeight();
        double weight=userDetails.getWeight();
        double bmi=weight/(height*height)*100;
        UserDetails userDetails1 =new UserDetails();
        userDetails1.setHeight(userDetails.getHeight());
        userDetails1.setWeight(userDetails.getWeight());
        userDetails1.setBmi_date(LocalDate.now());
        userDetails1.setBmi(bmi);
        userDetailsRepo.save(userDetails1);
        return new ResponseEntity<>(userDetails1, HttpStatus.OK);
    }


    public ResponseEntity<?> updateBmi(Integer user_id,double height, double weight) {
        Optional<UserDetails> userDetailsOptional = userDetailsRepo.findById(user_id);
        if (userDetailsOptional.isPresent()) {
            UserDetails userDetails = userDetailsOptional.get();
            double h=height/100;
            double bmi=weight/(h*h);
            userDetails.setHeight(height);
            userDetails.setWeight(weight);
            userDetails.setUpdated_bmi(bmi);
            userDetails.setBmi_Updated_date(LocalDate.now());
            userDetailsRepo.save(userDetails);
            return new ResponseEntity<>("Bmi updated", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("User not found ", HttpStatus.NOT_FOUND);
        }

    }


    public ResponseEntity<?> assignTrainer(Integer userId, Integer trainerId) {
        Optional<UserModel> userModelOptional = userRepo.findById(userId);
        Optional<TrainerModel> trainerModelOptional = trainerRepo.findById(trainerId);
        if (userModelOptional.isPresent() && trainerModelOptional.isPresent()) {
            UserModel userModel=userModelOptional.get();
            if (!userModel.isTrainer_reg_payment()) {
                return new ResponseEntity<>("Complete payment to assign a trainer.", HttpStatus.FORBIDDEN);
            }
            UserTrainerModel userTrainerModel = new UserTrainerModel();
            userTrainerModel.setUser(userModelOptional.get());
            userTrainerModel.setTrainer(trainerModelOptional.get());
            userTrainerRepo.save(userTrainerModel);
            return new ResponseEntity<>("Trainer assigned to user successful", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("User or Trainer not found", HttpStatus.NOT_FOUND);
        }
    }


    public ResponseEntity<?> updatePassword(String email, String password) {
        Optional<UserModel>userModelOptional=userRepo.findByEmail(email);
        if (userModelOptional.isPresent()){
            UserModel userModel=userModelOptional.get();
            userModel.setPassword(password);
            userRepo.save(userModel);
            return new ResponseEntity<>("password uodated successfully",HttpStatus.OK);
        }else{
            return new ResponseEntity<>("User Not found",HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<List<ExcerciseDto>> viewExcercises(Integer trainerId, Integer excerciseId) {
          return new ResponseEntity<>(new ArrayList<>(), HttpStatus.NOT_FOUND);
    }
}
