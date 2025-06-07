package com.WorkoutTracker.Service;

import com.WorkoutTracker.Dto.*;
import com.WorkoutTracker.Model.Bmi.BmiRepo;
import com.WorkoutTracker.Model.WorkoutDay.WorkoutDayModal;
import com.WorkoutTracker.Model.WorkoutDay.WorkoutDayRepo;
import com.WorkoutTracker.Model.ExerciseDetails.ExcerciseDetailsModel;
import com.WorkoutTracker.Model.ExerciseDetails.ExcerciseDetailsRepo;
import com.WorkoutTracker.Model.ExerciseSpecialization.ExcerciseSpecializationModel;
import com.WorkoutTracker.Model.ExerciseSpecialization.ExcerciseSpecializationRepo;
import com.WorkoutTracker.Model.Gender.GenderRepo;
import com.WorkoutTracker.Model.Trainer.TrainerModel;
import com.WorkoutTracker.Model.Trainer.TrainerRepo;
import com.WorkoutTracker.Model.User.UserModel;
import com.WorkoutTracker.Model.User.UserRepo;

import com.WorkoutTracker.Model.UserTrainer.UserTrainerModel;
import com.WorkoutTracker.Model.UserTrainer.UserTrainerRepo;

import com.WorkoutTracker.Model.Workouts.WorkoutModel;
import com.WorkoutTracker.Model.Workouts.WorkoutRepo;
import com.WorkoutTracker.Payment.PaymentModel;
import com.WorkoutTracker.Payment.PaymentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.*;

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

    @Autowired
    private WorkoutRepo workoutRepo;

    @Autowired
    private ExcerciseSpecializationRepo excerciseSpecializationRepo;

    @Autowired
    private GenderRepo genderRepo;



    @Autowired
    private WorkoutDayRepo workoutDayRepo;

    @Autowired
    private PaymentRepo paymentRepo;
    @Autowired
    private BmiRepo bmiRepo;


    private double calculateSalary(Integer experienceYears) {
        if (experienceYears <= 2) return 1000;
        else if (experienceYears <= 5) return 3000;
        else if (experienceYears <= 9) return 5000;
        else return 7000;
    }
    //add trainer
    public ResponseEntity<?> addDetails(TrainerModel trainerModel, MultipartFile certificationImage) throws IOException {
        TrainerModel trainerModel1 =new TrainerModel();
        trainerModel1.setName(trainerModel.getName());
        trainerModel1.setEmail(trainerModel.getEmail());
        trainerModel1.setPassword(trainerModel.getPassword());
        trainerModel1.setCertification(trainerModel.getCertification());
        trainerModel1.setExperienceYears(trainerModel.getExperienceYears());
        trainerModel1.setSpecialization_id(trainerModel.getSpecialization_id());
        trainerModel1.setCertificationImage(certificationImage.getBytes());
        trainerModel1.setCreated_date(LocalDate.now());
        trainerModel1.setMobile(trainerModel.getMobile());
        double salary = calculateSalary(trainerModel.getExperienceYears());
        trainerModel1.setSalary(salary);
        trainerRepo.save(trainerModel1);
        return new ResponseEntity<>(trainerModel1, HttpStatus.OK);
    }


    //login for trainers
    public ResponseEntity<?> loginDetails(LoginDto loginDto) {
        
        Optional<TrainerModel> trainerModelOptional = trainerRepo.findByEmailAndPassword(loginDto.getEmail(), loginDto.getPassword());
        if (trainerModelOptional.isPresent()){
            Integer trainer_id=trainerModelOptional.get().getTrainer_id();
            Integer statusID=trainerModelOptional.get().getStatusID();
            Map<String,Object> loginres=new HashMap<>();
            loginres.put("message","Login success");
            loginres.put("trainer_id",trainer_id);
            loginres.put("statusID",statusID);
            return new ResponseEntity<>(loginres, HttpStatus.OK);
        }else {
            return new ResponseEntity<>(" Trainer Credentials Incorrect",HttpStatus.NOT_FOUND);
        }
    }

    //Trainer Add Exercises
    public ResponseEntity<?> addExcercises(ExcerciseDetailsModel excerciseDetailsModel) {
        ExcerciseDetailsModel excerciseDetailsModel1=new ExcerciseDetailsModel();
        excerciseDetailsModel1.setExercise_name(excerciseDetailsModel.getExercise_name());
        excerciseDetailsModel1.setFocusarea(excerciseDetailsModel.getFocusarea());
        excerciseDetailsModel1.setTrainer_id(excerciseDetailsModel.getTrainer_id());
        excerciseDetailsRepo.save(excerciseDetailsModel1);
        return new ResponseEntity<>(excerciseDetailsModel1, HttpStatus.OK);

    }


    //trainer view exercises
    public ResponseEntity<List<ExcerciseDto>> getExercises(Integer trainerId) {
        List<ExcerciseDto> dto = new ArrayList<>();
        List<ExcerciseDetailsModel> excerciseDetailsModelList = excerciseDetailsRepo.findByTrainerId(trainerId);
        if (!excerciseDetailsModelList.isEmpty()) {
            for (ExcerciseDetailsModel excerciseDetailsModel : excerciseDetailsModelList) {
                ExcerciseDto excerciseDto = new ExcerciseDto();
                excerciseDto.setExercise_id(excerciseDetailsModel.getExercise_id());
                excerciseDto.setExercise_name(excerciseDetailsModel.getExercise_name());
                excerciseDto.setFocusarea(excerciseDetailsModel.getFocusarea());
                dto.add(excerciseDto);
            }

        }

        return new ResponseEntity<>(dto, HttpStatus.OK);
    }


    //trainer view clients
    public ResponseEntity<?> getAssignedUsers(Integer trainer_id) {
        List<UserDto> userDtoList = new ArrayList<>();
        List<UserTrainerModel> userTrainerModels = userTrainerRepo.findByTrainerId(trainer_id);

        if (!userTrainerModels.isEmpty()) {
            for (UserTrainerModel userTrainerModel : userTrainerModels) {
                Optional<UserModel> userModelOptional = userRepo.findById(userTrainerModel.getUser_id());

                if (userModelOptional.isPresent()) {
                    UserModel userModel = userModelOptional.get();
                    if (userModel.getName() == null) {
                        continue;
                    }

                    UserDto userDto = new UserDto();
                    userDto.setUser_id(userModel.getUser_id());
                    userDto.setName(userModel.getName());
                    userDto.setEmail(userModel.getEmail());
                    userDto.setPaymentStatus(userModel.getPaymentStatus());

                    genderRepo.findById(userModel.getGender_id()).ifPresent(genderModel -> {
                        userDto.setGender_name(genderModel.getGenderName());
                    });

                    bmiRepo.findLatestBmiByUserId(userModel.getUser_id()).ifPresent(bmiModel -> {
                        userDto.setWeight(bmiModel.getWeight());
                        userDto.setBmi(bmiModel.getBmi());
                    });

                    userDtoList.add(userDto);
                }
            }

            return new ResponseEntity<>(userDtoList, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(userDtoList, HttpStatus.NOT_FOUND);
        }
    }





    //trainer add achievements
    public ResponseEntity<?> addAchievements(Integer trainerId,String achievements) {
        Optional<TrainerModel> trainerModelOptional=trainerRepo.findById(trainerId);
        if (trainerModelOptional.isPresent()){
            TrainerModel trainerModel=trainerModelOptional.get();
            trainerModel.setAchievements(achievements);
            trainerRepo.save(trainerModel);
            return new ResponseEntity<>("Achievement Added Successfully",HttpStatus.OK);
        }
        return new ResponseEntity<>("Trainer Not Found",HttpStatus.NOT_FOUND);
    }



    //reset trainer password
    public ResponseEntity<?> updatePassword(String email, String password) {
        Optional<TrainerModel>trainerModelOptional=trainerRepo.findByEmail(email);
        if (trainerModelOptional.isPresent()){
            TrainerModel trainerModel=trainerModelOptional.get();
            trainerModel.setPassword(password);
            trainerRepo.save(trainerModel);
            return new ResponseEntity<>("password uodated successfully",HttpStatus.OK);
        }else{
            return new ResponseEntity<>("Trainer Not found",HttpStatus.NOT_FOUND);
        }
    }


    //trainer create workouts for assigned users
    public ResponseEntity<?> createWorkouts(WorkoutModel workoutModel) {
        WorkoutModel workoutModel1=new WorkoutModel();
        workoutModel1.setUser_id(workoutModel.getUser_id());
        workoutModel1.setExercise_id(workoutModel.getExercise_id());
        workoutModel1.setTrainer_id(workoutModel.getTrainer_id());
        workoutModel1.setId(workoutModel.getId());
        workoutModel1.setEquipments(workoutModel.getEquipments());
        workoutModel1.setCreated_date(LocalDate.now());
        workoutModel1.setDuration(workoutModel.getDuration());
        workoutModel1.setReps(workoutModel.getReps());
        workoutModel1.setSets(workoutModel.getSets());
        workoutModel1.setWeights(workoutModel.getWeights());
        workoutRepo.save(workoutModel1);
        return new ResponseEntity<>(workoutModel1, HttpStatus.OK);
    }



    public ResponseEntity<?> weeklyworkout(@RequestBody WorkoutDayModal workoutDayModal) {
        int count = workoutDayRepo.countByUser_IdAndTrainer_Id(workoutDayModal.getUser_id(), workoutDayModal.getTrainer_id());

        WorkoutDayModal workoutDayModal1 = new WorkoutDayModal();
        workoutDayModal1.setTrainer_id(workoutDayModal.getTrainer_id());
        workoutDayModal1.setUser_id(workoutDayModal.getUser_id());
        workoutDayModal1.setDate(workoutDayModal.getDate());
        workoutDayModal1.setWorkoutName(workoutDayModal.getWorkoutName());
        String nextDay = "Day " + (count + 1);
        workoutDayModal1.setDay(nextDay);
        workoutDayModal1.setDay(nextDay);

        workoutDayRepo.save(workoutDayModal1);


        return new ResponseEntity<>(workoutDayModal1, HttpStatus.OK);
    }




    public ResponseEntity<?> daylyWorkouts(Integer userId) {
        List<WorkoutDayModal> workoutDayModalList = workoutDayRepo.findByUser_Id(userId);
        List<DailyWorkoutDto> dtoList = new ArrayList<>();

        for (WorkoutDayModal workoutDayModal : workoutDayModalList) {
            DailyWorkoutDto dailyWorkoutDto = new DailyWorkoutDto();
            dailyWorkoutDto.setUser_id(workoutDayModal.getUser_id());
            dailyWorkoutDto.setTrainer_id(workoutDayModal.getTrainer_id());
            dailyWorkoutDto.setDay(workoutDayModal.getDay());
            dailyWorkoutDto.setWorkoutName(workoutDayModal.getWorkoutName());
            dailyWorkoutDto.setDate(workoutDayModal.getDate());
            dailyWorkoutDto.setId(workoutDayModal.getId());
            dtoList.add(dailyWorkoutDto);
        }

        return new ResponseEntity<>(dtoList, HttpStatus.OK);
    }




    //Delete workout scheduled by trainer
    public ResponseEntity<?> DeleteWorkouts(Integer workoutId,Integer trainer_id) {
        Optional<WorkoutModel>workoutModelOptional=workoutRepo.findById(workoutId);
        if (workoutModelOptional.isPresent()){
            WorkoutModel workoutModel = workoutModelOptional.get();
            if (!workoutModel.getTrainer_id().equals(trainer_id)) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Trainer is not allowed to delete this workout");
            }
            workoutRepo.delete(workoutModel);
            return new ResponseEntity<>("Workout Deleted Successfully",HttpStatus.OK);
        }else{
            return new ResponseEntity<>("Workout Not found",HttpStatus.NOT_FOUND);
        }

    }


    //View Profile trainer
    public ResponseEntity<?> ViewProfile(Integer trainerId) {
        Optional<TrainerModel> trainerModelOptional = trainerRepo.findById(trainerId);

        if (trainerModelOptional.isPresent()) {
            TrainerModel trainerModel = trainerModelOptional.get();
            TrainerDto trainerDto = new TrainerDto();
            trainerDto.setName(trainerModel.getName());
            trainerDto.setEmail(trainerModel.getEmail());
            trainerDto.setPassword(trainerModel.getPassword());
            trainerDto.setCertification(trainerModel.getCertification());
            trainerDto.setExperienceYears(trainerModel.getExperienceYears());
            trainerDto.setSalary(trainerModel.getSalary());

            Optional<ExcerciseSpecializationModel> excerciseSpecialisationModel = excerciseSpecializationRepo.findById(trainerModel.getSpecialization_id());
            if (excerciseSpecialisationModel.isPresent()) {
                trainerDto.setSpecialisationName(excerciseSpecialisationModel.get().getSpecialization_name());
            } else {
                trainerDto.setSpecialisationName("Not specified");
            }

            return new ResponseEntity<>(trainerDto, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
        }
    }

    //Edit Profile trainer
    public ResponseEntity<?> EditProfile(Integer trainerId, TrainerModel trainerModel) {
        Optional<TrainerModel> trainerModelOptional=trainerRepo.findById(trainerId);
        if (trainerModelOptional.isPresent()){
            TrainerModel trainerModel1=trainerModelOptional.get();
            trainerModel1.setName(trainerModel.getName());
            trainerModel1.setEmail(trainerModel.getEmail());
            trainerModel1.setPassword(trainerModel.getPassword());
            trainerModel1.setCertification(trainerModel.getCertification());
            trainerModel1.setSpecialization_id(trainerModel.getSpecialization_id());
            trainerModel1.setExperienceYears(trainerModel.getExperienceYears());
            trainerRepo.save(trainerModel1);
            return new ResponseEntity<>(trainerModel1, HttpStatus.OK);
        }

        return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
    }



    //Delete Account trainer
    public ResponseEntity<?> DeleteProfile(Integer trainerId) {
        Optional<TrainerModel> trainerModelOptional = trainerRepo.findById(trainerId);
        if (trainerModelOptional.isPresent()) {
            TrainerModel trainerModel=trainerModelOptional.get();
            trainerRepo.delete(trainerModel);
            return new ResponseEntity<>("Deleted Trainer Successfully",HttpStatus.OK);
        }else{
            return new ResponseEntity<>("Trainer Not found",HttpStatus.NOT_FOUND);
        }
    }

//view wokouts scheduled to users
    public ResponseEntity<?> viewWorkout(Integer userId,Integer Id) {
        List<WorkoutModel> workoutModelList = workoutRepo.findByUserIdAndId(userId,Id);
        List<WorkoutDto> dtos = new ArrayList<>();
        if (!workoutModelList.isEmpty()) {
            for (WorkoutModel workoutModel : workoutModelList) {
                WorkoutDto workoutDto = new WorkoutDto();

                workoutDto.setWorkout_id(workoutModel.getWorkout_id());
                workoutDto.setSets(workoutModel.getSets());
                workoutDto.setReps(workoutModel.getReps());
                workoutDto.setEquipments(workoutModel.getEquipments());
                workoutDto.setWeights(workoutModel.getWeights());
                workoutDto.setStatus(workoutModel.getStatus());
                workoutDto.setSetsDone(workoutModel.getSetsDone());
                workoutDto.setWorkoutStatus(workoutModel.getWorkoutStatus());
                workoutDto.setRemarks(workoutModel.getRemarks());
                workoutDto.setRepsDone(workoutModel.getRepsDone());

                Optional<ExcerciseDetailsModel> excerciseDetailsModelOptional = excerciseDetailsRepo.findById(workoutModel.getExercise_id());
                if (excerciseDetailsModelOptional.isPresent()) {
                    ExcerciseDetailsModel excerciseDetailsModel = excerciseDetailsModelOptional.get();
                    workoutDto.setExcercise_name(excerciseDetailsModel.getExercise_name());

                }
                Optional<WorkoutDayModal> dailyWorkoutOptional = workoutDayRepo.findById(workoutModel.getId());
                if (dailyWorkoutOptional.isPresent()) {
                    WorkoutDayModal workoutDayModal = dailyWorkoutOptional.get();
                    workoutDto.setDate(workoutDayModal.getDate());

                }
                dtos.add(workoutDto);
            }
            return new ResponseEntity<>(dtos, HttpStatus.OK);
        }

        return new ResponseEntity<>("No workouts found", HttpStatus.NOT_FOUND);
    }


    public ResponseEntity<List<ExcerciseSpecializationModel>> viewSpecialisation() {
        List<ExcerciseSpecializationModel> dto = new ArrayList<>();
        List<ExcerciseSpecializationModel> excerciseSpecializationModels = excerciseSpecializationRepo.findAll();
        if (!excerciseSpecializationModels.isEmpty()) {
            for (ExcerciseSpecializationModel excerciseSpecializationModel : excerciseSpecializationModels) {
                ExcerciseSpecializationModel excerciseSpecializationModel1 = new ExcerciseSpecializationModel();
                excerciseSpecializationModel1.setSpecialization_id(excerciseSpecializationModel.getSpecialization_id());
                excerciseSpecializationModel1.setSpecialization_name(excerciseSpecializationModel.getSpecialization_name());

                dto.add(excerciseSpecializationModel);
            }

        }

        return new ResponseEntity<>(dto, HttpStatus.OK);

    }

    public ResponseEntity<?> description(Integer trainerId, String about) {
        Optional<TrainerModel> trainerModelOptional = trainerRepo.findById(trainerId);
        if (trainerModelOptional.isPresent()) {
            TrainerModel trainerModel = trainerModelOptional.get();
            trainerModel.setAbout(about);
            trainerRepo.save(trainerModel);
            return new ResponseEntity<>("description added successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Trainer Not found", HttpStatus.NOT_FOUND);
        }
    }



    public ResponseEntity<?> showdescription(Integer trainerId) {
        Optional<TrainerModel> trainerModelOptional = trainerRepo.findById(trainerId);
        if (trainerModelOptional.isPresent()) {
            TrainerModel trainerModel = trainerModelOptional.get();
            String about=trainerModel.getAbout();
            return new ResponseEntity<>(about, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Trainer Not found", HttpStatus.NOT_FOUND);
        }

    }

    public ResponseEntity<?> deleteExercise(Integer trainerId,Integer exercise_id) {
        Optional<ExcerciseDetailsModel> excerciseDetailsModelOptional=excerciseDetailsRepo.findByTrainerIdAndExercise_Id(trainerId,exercise_id);
        if (excerciseDetailsModelOptional.isPresent()){
            ExcerciseDetailsModel excerciseDetailsModel=excerciseDetailsModelOptional.get();
            excerciseDetailsRepo.delete(excerciseDetailsModel);
            return new ResponseEntity<>("Exercise deleted",HttpStatus.OK);

        }
        return new ResponseEntity<>("No exercise found for this trainer",HttpStatus.NO_CONTENT);
    }


//    public ResponseEntity<?> notScheduledToday(Integer trainerId, LocalDate date) {
//        List<UserTrainerModel> userTrainerModelList=userTrainerRepo.findByTrainerId(trainerId);
//
//    }


    public List<UserInfoDto> getUnscheduledUsers(Integer trainerId) {
        return userRepo.findUsersWithoutWorkoutToday(trainerId, LocalDate.now());
    }

    public List<UserDto> getPaymentStatus(Integer trainer_id) {
        List<UserDto> userDtos = new ArrayList<>();
        List<UserTrainerModel> userTrainerModelList = userTrainerRepo.findByTrainer_Id(trainer_id);

        if (!userTrainerModelList.isEmpty()) {
            for (UserTrainerModel userTrainerModel : userTrainerModelList) {
                UserDto userDto = new UserDto();
                Optional<UserModel> userModelOptional = userRepo.findById(userTrainerModel.getUser_id());

                if (userModelOptional.isPresent()) {
                    UserModel userModel = userModelOptional.get();
                    String status="SUCCESS";
                    Optional<PaymentModel> paymentModelOptional=paymentRepo.findByUser_IdAndPaymentStatus(userTrainerModel.getUser_id(),status);
                    if (paymentModelOptional.isPresent()){
                        PaymentModel paymentModel=paymentModelOptional.get();
                        userDto.setPaymentStatus(userModel.getPaymentStatus());
                        userDto.setUser_id(userModel.getUser_id());
                        userDto.setName(userModel.getName());
                        userDto.setSubscriptionStart(paymentModel.getSubscriptionStart());
                        userDto.setSubscriptionEnd(paymentModel.getSubscriptionEnd());
                        userDtos.add(userDto);
                    }


                }



            }
        }

        return userDtos;
    }

}
