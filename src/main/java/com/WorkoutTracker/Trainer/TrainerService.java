package com.WorkoutTracker.Trainer;

import com.WorkoutTracker.Dto.*;
import com.WorkoutTracker.Exercises.ExerciseDetails.ExcerciseDetailsModel;
import com.WorkoutTracker.Exercises.ExerciseDetails.ExcerciseDetailsRepo;
import com.WorkoutTracker.Exercises.Specialization.ExcerciseSpecialisationModel;
import com.WorkoutTracker.Exercises.Specialization.ExcerciseSpecializationRepo;
import com.WorkoutTracker.Gender.GenderModel;
import com.WorkoutTracker.Gender.GenderRepo;
import com.WorkoutTracker.User.Registration.UserModel;
import com.WorkoutTracker.User.Registration.UserRepo;
import com.WorkoutTracker.User.UserDetails.UserDetails;
import com.WorkoutTracker.User.UserDetails.UserDetailsRepo;
import com.WorkoutTracker.UserTrainer.UserTrainerModel;
import com.WorkoutTracker.UserTrainer.UserTrainerRepo;
import com.WorkoutTracker.WeekDays.WeekDaysModel;
import com.WorkoutTracker.WeekDays.WeekDaysRepo;
import com.WorkoutTracker.Workouts.WorkoutModel;
import com.WorkoutTracker.Workouts.WorkoutRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

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
    private WeekDaysRepo weekDaysRepo;

    @Autowired
    private UserDetailsRepo userDetailsRepo;


    //add trainer
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
        excerciseDetailsModel1.setExercise_description(excerciseDetailsModel.getExercise_description());
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
                excerciseDto.setExercise_description(excerciseDetailsModel.getExercise_description());
                excerciseDto.setFocusarea(excerciseDetailsModel.getFocusarea());
                dto.add(excerciseDto);
            }

        }

        return new ResponseEntity<>(dto, HttpStatus.OK);
    }


    //trainer view Assigned Users
    public ResponseEntity<?> getAssignedUsers(Integer trainer_id) {
        List<UserDto> userDtoList = new ArrayList<>();
        List<UserTrainerModel>userTrainerModels=userTrainerRepo.findByTrainerId(trainer_id);
        if (!userTrainerModels.isEmpty()) {
            for (UserTrainerModel userTrainerModel : userTrainerModels) {
                UserDto userDto=new UserDto();
                userDto.setUser_id(userTrainerModel.getUser_id());
                Optional<UserModel> userModelOptional=userRepo.findById(userTrainerModel.getUser_id());
                if (userModelOptional.isPresent()) {
                    UserModel userModel = userModelOptional.get();
                    userDto.setName(userModel.getName());
                    userDto.setEmail(userModel.getEmail());
                    Optional<GenderModel> genderModelOptional = genderRepo.findById(userModel.getGender_id());
                    if (genderModelOptional.isPresent()) {
                        GenderModel genderModel = genderModelOptional.get();
                        userDto.setGender_name(genderModel.getGenderName());
                    }
                    Optional<UserDetails> userDetailsOptional = userDetailsRepo.findByUser_Id(userModel.getUser_id());
                    if (userDetailsOptional.isPresent()) {
                        UserDetails userDetails = userDetailsOptional.get();
                        userDto.setWeight(userDetails.getWeight());
                        userDto.setBmi(userDetails.getBmi());
                    }

                }
                userDtoList.add(userDto);
            }
            return new ResponseEntity<>(userDtoList, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(userDtoList, HttpStatus.NOT_FOUND);
        }

    }

    //trainer select assigned users
    public ResponseEntity<?> selectUser(Integer trainerId, Integer userId) {
        Optional<UserModel> userModelOptional = userRepo.findById(userId);
        Optional<TrainerModel> trainerModelOptional = trainerRepo.findById(trainerId);
        if (userModelOptional.isPresent() && trainerModelOptional.isPresent()) {
            return new ResponseEntity<>("User Selected", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("User or Trainer not found", HttpStatus.NOT_FOUND);
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


    //trainer schedule workouts for assigned users
    public ResponseEntity<?> scheduleWorkouts(WorkoutModel workoutModel) {
        WorkoutModel workoutModel1=new WorkoutModel();
        workoutModel1.setUser_id(workoutModel.getUser_id());
        workoutModel1.setExercise_id(workoutModel.getExercise_id());
        workoutModel1.setTrainer_id(workoutModel.getTrainer_id());
        workoutModel1.setWeekdayId(workoutModel.getWeekdayId());
        workoutModel1.setEquipments(workoutModel.getEquipments());
        workoutModel1.setCreated_date(LocalDate.now());
        workoutModel1.setWorkoutdate(workoutModel.getWorkoutdate());
        workoutModel1.setDuration(workoutModel.getDuration());
        workoutModel1.setReps(workoutModel.getReps());
        workoutModel1.setSets(workoutModel.getSets());
        workoutModel1.setWeights(workoutModel.getWeights());
        workoutModel1.setWorkoutdate(workoutModel.getWorkoutdate());
        workoutRepo.save(workoutModel1);
        return new ResponseEntity<>(workoutModel1, HttpStatus.OK);
    }



    public ResponseEntity<?> addWeekdays(@RequestBody List<WeekDaysModel> weekDaysModels) {
        List<WeekDaysModel> savedWeekdays = new ArrayList<>();

        for (WeekDaysModel weekDaysModel : weekDaysModels) {
            WeekDaysModel newWeekDay = new WeekDaysModel();
            newWeekDay.setTrainer_id(weekDaysModel.getTrainer_id());
            newWeekDay.setUser_id(weekDaysModel.getUser_id());
            newWeekDay.setWeek(weekDaysModel.getWeek());
            newWeekDay.setDay(weekDaysModel.getDay());
            newWeekDay.setName(weekDaysModel.getName());

            savedWeekdays.add(weekDaysRepo.save(newWeekDay));
        }

        return new ResponseEntity<>(savedWeekdays, HttpStatus.OK);
    }



    public ResponseEntity<?> getWeekDays(Integer userId, String week) {
        List<WeekDaysModel> weekDaysList = weekDaysRepo.findByUser_IdAndWeek(userId, week);
        List<WeeKDayDto> dtoList = new ArrayList<>();

        for (WeekDaysModel weekDaysModel : weekDaysList) {
            WeeKDayDto weeKDayDto = new WeeKDayDto();
            weeKDayDto.setUser_id(weekDaysModel.getUser_id());
            weeKDayDto.setTrainer_id(weekDaysModel.getTrainer_id());
            weeKDayDto.setDay(weekDaysModel.getDay());
            weeKDayDto.setWeek(weekDaysModel.getWeek());
            weeKDayDto.setName(weekDaysModel.getName());
            weeKDayDto.setWeekdayId(weekDaysModel.getWeekdayId());
            dtoList.add(weeKDayDto);
        }

        return new ResponseEntity<>(dtoList, HttpStatus.OK);
    }
    public ResponseEntity<?> getWeekDay(Integer userId) {
        List<WeekDaysModel> weekDaysList = weekDaysRepo.findByUser_Id(userId);
        List<WeeKDayDto> dtoList = new ArrayList<>();

        for (WeekDaysModel weekDaysModel : weekDaysList) {
            WeeKDayDto weeKDayDto = new WeeKDayDto();
            weeKDayDto.setUser_id(weekDaysModel.getUser_id());
            weeKDayDto.setTrainer_id(weekDaysModel.getTrainer_id());
            weeKDayDto.setDay(weekDaysModel.getDay());
            weeKDayDto.setWeek(weekDaysModel.getWeek());
            weeKDayDto.setName(weekDaysModel.getName());
            weeKDayDto.setWeekdayId(weekDaysModel.getWeekdayId());
            dtoList.add(weeKDayDto);
        }

        return new ResponseEntity<>(dtoList, HttpStatus.OK);
    }

    //trainer update scheduled workouts of user

    public ResponseEntity<?> UpdateWorkouts(Integer workoutId, Integer trainerId, Integer userId, WorkoutUpdateDto workoutUpdateDto) {
            Optional<WorkoutModel> workoutModelOptional = workoutRepo.findById(workoutId);
            if (workoutModelOptional.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Workout not found");
            }
            WorkoutModel workoutModel = workoutModelOptional.get();
            if (!workoutModel.getTrainer_id().equals(trainerId)) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Trainer is not allowed to update workout");
            }
            if (!workoutModel.getUser_id().equals(userId)) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Workout does not belong to the specified user");
            }
            workoutModel.setExercise_id(workoutUpdateDto.getExercise_id());
            workoutModel.setDuration(workoutUpdateDto.getDuration());
            workoutModel.setUpdated_date(LocalDate.now());
            workoutRepo.save(workoutModel);
            return new ResponseEntity<>(workoutModel,HttpStatus.OK);
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

            Optional<ExcerciseSpecialisationModel> excerciseSpecialisationModel = excerciseSpecializationRepo.findById(trainerModel.getSpecialization_id());
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
    public ResponseEntity<?> viewWorkout(Integer userId,Integer weekdayId) {
        List<WorkoutModel> workoutModelList = workoutRepo.findByUserIdAndWeekdayId(userId,weekdayId);
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

                Optional<ExcerciseDetailsModel> excerciseDetailsModelOptional = excerciseDetailsRepo.findById(workoutModel.getExercise_id());
                if (excerciseDetailsModelOptional.isPresent()) {
                    ExcerciseDetailsModel excerciseDetailsModel = excerciseDetailsModelOptional.get();
                    workoutDto.setExcercise_name(excerciseDetailsModel.getExercise_name());

                }
                dtos.add(workoutDto);
            }
            return new ResponseEntity<>(dtos, HttpStatus.OK);
        }

        return new ResponseEntity<>("No workouts found", HttpStatus.NOT_FOUND);
    }


    public ResponseEntity<List<ExcerciseSpecialisationModel>> viewSpecialisation() {
        List<ExcerciseSpecialisationModel> dto = new ArrayList<>();
        List<ExcerciseSpecialisationModel> excerciseSpecialisationModels = excerciseSpecializationRepo.findAll();
        if (!excerciseSpecialisationModels.isEmpty()) {
            for (ExcerciseSpecialisationModel excerciseSpecialisationModel : excerciseSpecialisationModels) {
                ExcerciseSpecialisationModel excerciseSpecialisationModel1 = new ExcerciseSpecialisationModel();
                excerciseSpecialisationModel1.setSpecialization_id(excerciseSpecialisationModel.getSpecialization_id());
                excerciseSpecialisationModel1.setSpecialization_name(excerciseSpecialisationModel.getSpecialization_name());

                dto.add(excerciseSpecialisationModel);
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

    public ResponseEntity<?> specialities(Integer trainerId, String specialities) {
        Optional<TrainerModel> trainerModelOptional = trainerRepo.findById(trainerId);
        if (trainerModelOptional.isPresent()) {
            TrainerModel trainerModel = trainerModelOptional.get();
            trainerModel.setAbout(specialities);
            trainerRepo.save(trainerModel);
            return new ResponseEntity<>("specialities added successfully", HttpStatus.OK);
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
}
