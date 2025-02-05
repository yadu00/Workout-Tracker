package com.WorkoutTracker.Trainer;

import com.WorkoutTracker.Dto.UserDto;
import com.WorkoutTracker.Excercises.ExcerciseDetails.ExcerciseDetailsModel;
import com.WorkoutTracker.Excercises.ExcerciseDetails.ExcerciseDetailsRepo;
import com.WorkoutTracker.User.Registration.UserModel;
import com.WorkoutTracker.User.Registration.UserRepo;
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
public class TrainerService {
    @Autowired
    private TrainerRepo trainerRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private ExcerciseDetailsRepo excerciseDetailsRepo;

    @Autowired
    private UserTrainerRepo userTrainerRepo;





    public ResponseEntity<?> addDetails(TrainerModel trainerModel) {
        TrainerModel trainerModel1 =new TrainerModel();
        trainerModel1.setName(trainerModel.getName());
        trainerModel1.setEmail(trainerModel.getEmail());
        trainerModel1.setPassword(trainerModel.getPassword());
        trainerModel1.setCertification(trainerModel.getCertification());
        trainerModel1.setExperienceYears(trainerModel.getExperienceYears());
        trainerModel1.setSpecialization_id(trainerModel.getSpecialization_id());
        trainerModel1.setCreated_date(LocalDate.now());
        trainerRepo.save(trainerModel1);
        return new ResponseEntity<>(trainerModel1, HttpStatus.OK);
    }



    public ResponseEntity<?> loginDetails(String email, String password) {
        Optional<TrainerModel> trainerModelOptional = trainerRepo.findByEmailAndPassword(email,password);
        if (trainerModelOptional.isPresent()){
            return new ResponseEntity<>("Login success", HttpStatus.OK);
        }else {
            return new ResponseEntity<>(" Trainer Credentials Incorrect",HttpStatus.NOT_FOUND);
        }
    }


    public ResponseEntity<?> addExcercises(ExcerciseDetailsModel excerciseDetailsModel) {
        ExcerciseDetailsModel excerciseDetailsModel1=new ExcerciseDetailsModel();
        excerciseDetailsModel1.setExercise_name(excerciseDetailsModel.getExercise_name());
        excerciseDetailsModel1.setExercise_description(excerciseDetailsModel.getExercise_description());
        excerciseDetailsModel1.setReps(excerciseDetailsModel.getReps());
        excerciseDetailsModel1.setSets(excerciseDetailsModel.getSets());
        excerciseDetailsModel1.setWeights(excerciseDetailsModel.getWeights());
        excerciseDetailsRepo.save(excerciseDetailsModel1);
        return new ResponseEntity<>(excerciseDetailsModel1, HttpStatus.OK);

    }

    public ResponseEntity<?> getAssignedUsers(Integer trainer_id) {
        List<UserDto> userDtoList = new ArrayList<>();
        List<UserTrainerModel>userTrainerModels=userTrainerRepo.findByTrainer_trainerId(trainer_id);
        if (!userTrainerModels.isEmpty()) {
            for (UserTrainerModel userTrainerModel : userTrainerModels) {
                UserModel userModel=userTrainerModel.getUser();
                userDtoList.add(new UserDto(userModel.getName(), userModel.getEmail()));
            }
            return new ResponseEntity<>(userDtoList, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(userDtoList, HttpStatus.NOT_FOUND);
        }

    }

    public ResponseEntity<?> selectUser(Integer trainerId, Integer userId) {
        Optional<UserModel> userModelOptional = userRepo.findById(userId);
        Optional<TrainerModel> trainerModelOptional = trainerRepo.findById(trainerId);
        if (userModelOptional.isPresent() && trainerModelOptional.isPresent()) {
            return new ResponseEntity<>("User Selected", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("User or Trainer not found", HttpStatus.NOT_FOUND);
        }
    }


    public ResponseEntity<?> addAchievements(Integer trainerId) {
        Optional<TrainerModel> trainerModelOptional=trainerRepo.findById(trainerId);
        if (trainerModelOptional.isPresent()){
            TrainerModel trainerModel=new TrainerModel();
            trainerModel.setAchievements(trainerModel.getAchievements());
            trainerRepo.save(trainerModel);
            return new ResponseEntity<>("Achievement Added Successfully",HttpStatus.OK);
        }
        return new ResponseEntity<>("Trainer Not Found",HttpStatus.NOT_FOUND);
    }
}
