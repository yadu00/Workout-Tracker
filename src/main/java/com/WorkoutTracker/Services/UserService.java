package com.WorkoutTracker.Services;

import com.WorkoutTracker.Dto.*;

import com.WorkoutTracker.Exercises.ExerciseDetails.ExcerciseDetailsModel;
import com.WorkoutTracker.Exercises.ExerciseDetails.ExcerciseDetailsRepo;
import com.WorkoutTracker.Exercises.Specialization.ExcerciseSpecialisationModel;
import com.WorkoutTracker.Exercises.Specialization.ExcerciseSpecializationRepo;
import com.WorkoutTracker.Gender.GenderModel;
import com.WorkoutTracker.Gender.GenderRepo;
import com.WorkoutTracker.Trainer.TrainerModel;
import com.WorkoutTracker.Reports.UserDetails;
import com.WorkoutTracker.Trainer.TrainerRepo;
import com.WorkoutTracker.Reports.UserDetailsRepo;
import com.WorkoutTracker.User.UserModel;
import com.WorkoutTracker.User.UserRepo;
import com.WorkoutTracker.UserTrainer.UserTrainerModel;
import com.WorkoutTracker.UserTrainer.UserTrainerRepo;
import com.WorkoutTracker.WeekDays.WeekDaysModel;
import com.WorkoutTracker.WeekDays.WeekDaysRepo;
import com.WorkoutTracker.WeightLog.WeightModel;
import com.WorkoutTracker.WeightLog.WeightRepo;
import com.WorkoutTracker.Workouts.WorkoutModel;
import com.WorkoutTracker.Workouts.WorkoutRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@Service
@CrossOrigin(origins = "http://localhost:8081")

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
    private WorkoutRepo workoutRepo;

    @Autowired
    private GenderRepo genderRepo;

    @Autowired
    private WeekDaysRepo weekDaysRepo;

    @Autowired
    private WeightRepo weightRepo;


    //add user account
    public ResponseEntity<?> addDetails(UserModel userModel) {
        UserModel userModel1 = new UserModel();
        userModel1.setName(userModel.getName());
        userModel1.setEmail(userModel.getEmail());
        userModel1.setPassword(userModel.getPassword());
        userModel1.setGender_id(userModel.getGender_id());
        userModel1.setCreated_date(LocalDate.now());
        userRepo.save(userModel1);
        return new ResponseEntity<>(userModel1, HttpStatus.OK);
    }


    //login user account
    public ResponseEntity<?> loginDetails(LoginDto loginDto) {
        Optional<UserModel> userModelOptional = userRepo.findByEmailAndPassword(loginDto.getEmail(), loginDto.getPassword());
        if (userModelOptional.isPresent()) {
            Integer user_id = userModelOptional.get().getUser_id();
            Map<String, Object> loginres = new HashMap<>();
            loginres.put("message", "Login success");
            loginres.put("user_id", user_id);
            return new ResponseEntity<>(loginres, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(" User Credentials Incorrect", HttpStatus.NOT_FOUND);
        }
    }


    //select specializations
    public ResponseEntity<List<ExcerciseSpecialisationModel>> getAllSpecialization() {
        List<ExcerciseSpecialisationModel> excerciseSpecialisationModels = excerciseSpecializationRepo.findAll();
        if (!excerciseSpecialisationModels.isEmpty()) {
            return new ResponseEntity<>(excerciseSpecialisationModels, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }


    //list trainers according to specialization
    public ResponseEntity<List<TrainerDto>> filterTrainers(Integer specialization_id) {
        List<TrainerDto> dto = new ArrayList<>();
        List<TrainerModel> trainerModels = trainerRepo.findBySpecializationId(specialization_id);
        if (!trainerModels.isEmpty()) {
            for (TrainerModel trainerModel : trainerModels) {
                    TrainerDto trainerDto1 = new TrainerDto();
                    trainerDto1.setName(trainerModel.getName());
                    trainerDto1.setCertification(trainerModel.getCertification());
                    trainerDto1.setSpecialization(trainerModel.getSpecialization_id());
                    trainerDto1.setExperienceYears(trainerModel.getExperienceYears());
                List<UserTrainerModel> userTrainerModelList = userTrainerRepo.findByTrainerId(trainerModel.getTrainer_id());
                Integer sum = 0;
                Integer count = 0;

                for (UserTrainerModel userTrainerModel : userTrainerModelList) {
                    Integer rating = userTrainerModel.getRating();
                    if (rating != null) {
                        sum += rating;
                        count++;
                    }
                }

                Integer average = count > 0 ? sum / count : 0;
                trainerDto1.setRating(average);
                    dto.add(trainerDto1);
                }

            }

        return new ResponseEntity<>(dto, HttpStatus.OK);

    }


    //add Bmi details
    public ResponseEntity<?> addBmi(double height, double weight, Integer userId) {
        UserDetails userDetails = new UserDetails();
        double h = height / 100;
        double w = weight;
        double bmi = w / (h * h);
        userDetails.setUser_id(userId);
        userDetails.setHeight(height);
        userDetails.setWeight(weight);
        userDetails.setBmi_date(LocalDate.now());

        userDetails.setBmi(bmi);
        userDetailsRepo.save(userDetails);
        return new ResponseEntity<>(userDetails, HttpStatus.OK);


    }


    //Update bmi details
    public ResponseEntity<?> updateBmi(Integer user_id, double height, double weight) {
        Optional<UserDetails> userDetailsOptional = userDetailsRepo.findById(user_id);
        if (userDetailsOptional.isPresent()) {
            UserDetails userDetails = userDetailsOptional.get();
            double h = height / 100;
            double bmi = weight / (h * h);
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


    //Assigning a trainer
    public ResponseEntity<?> assignTrainer(Integer userId, Integer trainerId) {
        Optional<UserModel> userModelOptional = userRepo.findById(userId);
        Optional<TrainerModel> trainerModelOptional = trainerRepo.findById(trainerId);
        if (userModelOptional.isPresent() && trainerModelOptional.isPresent()) {
//            UserModel userModel=userModelOptional.get();
//            if (!userModel.isTrainer_reg_payment()) {
//                return new ResponseEntity<>("Complete payment to assign a trainer.", HttpStatus.FORBIDDEN);
//            }
            UserTrainerModel userTrainerModel = new UserTrainerModel();
            userTrainerModel.setUser_id(userModelOptional.get().getUser_id());
            userTrainerModel.setTrainer_id(trainerModelOptional.get().getTrainer_id());
            userTrainerRepo.save(userTrainerModel);
            return new ResponseEntity<>("Trainer assigned to user successful", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("User or Trainer not found", HttpStatus.NOT_FOUND);
        }
    }


    //update password
    public ResponseEntity<?> updatePassword(String email, String password) {
        Optional<UserModel> userModelOptional = userRepo.findByEmail(email);
        if (userModelOptional.isPresent()) {
            UserModel userModel = userModelOptional.get();
            userModel.setPassword(password);
            userRepo.save(userModel);
            return new ResponseEntity<>("password uodated successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("User Not found", HttpStatus.NOT_FOUND);
        }
    }


    //user view trainer excercises
    public ResponseEntity<List<ExcerciseDto>> viewExcercises(Integer trainerId) {
        Optional<TrainerModel> trainerModelOptional = trainerRepo.findById(trainerId);
        if (trainerModelOptional.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        List<ExcerciseDto> dtos = new ArrayList<>();
        List<ExcerciseDetailsModel> excerciseDetailsModels = excerciseDetailsRepo.findByTrainerId(trainerId);

        if (!excerciseDetailsModels.isEmpty()) {
            for (ExcerciseDetailsModel excerciseDetailsModel : excerciseDetailsModels) {
                ExcerciseDto excerciseDto = new ExcerciseDto();
                excerciseDto.setExercise_name(excerciseDetailsModel.getExercise_name());
                excerciseDto.setExercise_description(excerciseDetailsModel.getExercise_description());

                dtos.add(excerciseDto);
            }

            return new ResponseEntity<>(dtos, HttpStatus.OK);
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.NOT_FOUND);
    }


    //user update payment details
    public ResponseEntity<?> updatePayment(Integer userId, Boolean paymentStatus) {
        Optional<UserModel> userModelOptional = userRepo.findById(userId);
        if (userModelOptional.isPresent()) {
            UserModel userModel = userModelOptional.get();
            userModel.setTrainer_reg_payment(paymentStatus);
            userRepo.save(userModel);
            return new ResponseEntity<>("Payment status updated successfully", HttpStatus.OK);
        }
        return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
    }






    public ResponseEntity<List<TrainerDto>> getallTrainers() {
        List<TrainerDto> dto = new ArrayList<>();
        List<TrainerModel> trainerModels = trainerRepo.findAll();

        for (TrainerModel trainerModel : trainerModels) {
            TrainerDto trainerDto1 = new TrainerDto();
            trainerDto1.setTrainer_id(trainerModel.getTrainer_id());
            trainerDto1.setName(trainerModel.getName());
            trainerDto1.setCertification(trainerModel.getCertification());
            trainerDto1.setExperienceYears(trainerModel.getExperienceYears());

            List<UserTrainerModel> userTrainerModelList = userTrainerRepo.findByTrainerId(trainerModel.getTrainer_id());
            Integer sum = 0;
            Integer count = 0;

            for (UserTrainerModel userTrainerModel : userTrainerModelList) {
                Integer rating = userTrainerModel.getRating();
                if (rating != null) {
                    sum += rating;
                    count++;
                }
            }

            Integer average = count > 0 ? sum / count : 0;
            trainerDto1.setRating(average);

            dto.add(trainerDto1);
        }

        return new ResponseEntity<>(dto, HttpStatus.OK);
    }



    //Edit Profile user
    public ResponseEntity<?> EditProfile(Integer userId, UserModel userModel) {
        Optional<UserModel> userModelOptional = userRepo.findById(userId);
        if (userModelOptional.isPresent()) {
            UserModel userModel1 = userModelOptional.get();

            userModel1.setName(userModel.getName());
            userModel1.setEmail(userModel.getEmail());
            userModel1.setPassword(userModel.getPassword());
            userModel1.setGender_id(userModel.getGender_id());
            userRepo.save(userModel1);
            return new ResponseEntity<>(userModel1, HttpStatus.OK);
        }

        return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
    }

    //View Profile user
    public ResponseEntity<?> ViewProfile(Integer userId) {
        Optional<UserModel> userModelOptional = userRepo.findById(userId);

        if (userModelOptional.isPresent()) {
            UserModel userModel = userModelOptional.get();
            UserDto userDto = new UserDto();
            userDto.setName(userModel.getName());
            userDto.setEmail(userModel.getEmail());
            userDto.setPassword(userModel.getPassword());

            Optional<GenderModel> genderModelOptional = genderRepo.findById(userModel.getGender_id());
            if (genderModelOptional.isPresent()) {
                userDto.setGender_name(genderModelOptional.get().getGenderName());
            } else {
                userDto.setGender_name("Not specified");
            }

            return new ResponseEntity<>(userDto, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
        }
    }


    //Delete Account user
    public ResponseEntity<?> DeleteProfile(Integer userId) {
        Optional<UserModel> userModelOptional = userRepo.findById(userId);
        if (userModelOptional.isPresent()) {
            UserModel userModel = userModelOptional.get();
            userRepo.delete(userModel);
            return new ResponseEntity<>("Deleted User Successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("User Not found", HttpStatus.NOT_FOUND);
        }
    }




    public ResponseEntity<?> viewBmi(Integer userId) {
        Optional<UserDetails> userDetailsOptional = userDetailsRepo.findByUser_Id(userId);
        if (!userDetailsOptional.isEmpty()) {
            UserDetails userDetails = userDetailsOptional.get();
            UserDto userDto = new UserDto();
            userDto.setBmi(userDetails.getBmi());
            userDto.setWeight(userDetails.getWeight());
            userDto.setHeight(userDetails.getHeight());
            return new ResponseEntity<>(userDto, HttpStatus.OK);

        }

        return new ResponseEntity<>("No Bmi Found", HttpStatus.NO_CONTENT);
    }


    public ResponseEntity<?> viewWorkouts(Integer userId, Integer weekdayId) {
        List<WorkoutModel> workoutModelList = workoutRepo.findByUserIdAndWeekdayId(userId, weekdayId);
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
                workoutDto.setDate(workoutModel.getWorkoutdate());

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

    public ResponseEntity<?> getWeekDays(Integer userId) {
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

    public ResponseEntity<?> ViewAssignedTrainer(Integer userId) {
        Optional<UserTrainerModel> userTrainerModelOptional = userTrainerRepo.findByUserId(userId);
        if (userTrainerModelOptional.isPresent()) {
            UserTrainerModel userTrainerModel = userTrainerModelOptional.get();
            Optional<TrainerModel> trainerModelOptional = trainerRepo.findById(userTrainerModel.getTrainer_id());

            if (trainerModelOptional.isPresent()) {
                TrainerModel trainerModel = trainerModelOptional.get();
                TrainerDto trainerDto = new TrainerDto();
                trainerDto.setName(trainerModel.getName());
                trainerDto.setEmail(trainerModel.getEmail());
                trainerDto.setExperienceYears(trainerModel.getExperienceYears());
                trainerDto.setCertification(trainerModel.getCertification());

                return new ResponseEntity<>(trainerDto, HttpStatus.OK); // Return a single object
            }
        }
        return new ResponseEntity<>("Trainer not found", HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<?> UpdateWorkoutStatus(Integer userId,Integer workout_id) {
        Optional<WorkoutModel> workoutModelOptional = workoutRepo.findByUser_IdAndWorkout_id(userId,workout_id);
        if (workoutModelOptional.isPresent()) {
            WorkoutModel workoutModel = workoutModelOptional.get();
            workoutModel.setStatus(2);
            workoutRepo.save(workoutModel);
            return new ResponseEntity<>("Status updated successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>(" Not found", HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<Integer> totalworkoutsdone(Integer userId) {
        Integer count = workoutRepo.countByUserIdAndStatus(userId, 2);
        return ResponseEntity.ok(count);
    }

    public ResponseEntity<?> totalworkoutspending(Integer userId) {
        Integer count = workoutRepo.countByUserIdAndStatus(userId, 1);
        return ResponseEntity.ok(count);
    }

    public ResponseEntity<?> viewtodaysworkouts(Integer userId, LocalDate workoutdate) {
        List<WorkoutModel> workoutModelList = workoutRepo.findByUserIdAndWorkoutdate(userId, workoutdate);
        List<WorkoutDto> dtos = new ArrayList<>();
        if (!workoutModelList.isEmpty()) {
            for (WorkoutModel workoutModel : workoutModelList) {
                WorkoutDto workoutDto = new WorkoutDto();
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

    public ResponseEntity<?> logWeight(WeightModel weightModel,Integer user_id) {
        WeightModel weightModel1=new WeightModel();
        weightModel1.setUser_id(user_id);
        weightModel1.setWeight(weightModel.getWeight());
        weightModel1.setLogDate(LocalDate.now());
        weightModel1.setBmi(weightModel.getBmi());
        weightRepo.save(weightModel1);
        return new ResponseEntity<>(weightModel1, HttpStatus.OK);
    }

    public ResponseEntity<?> weightHistory(Integer userId) {
        List<WeightModel> weightHistory = weightRepo.findAllByUser_Id(userId);

        if (weightHistory != null && !weightHistory.isEmpty()) {
            return new ResponseEntity<>(weightHistory, HttpStatus.OK);
        }

        return new ResponseEntity<>("No details found", HttpStatus.NOT_FOUND);
    }


    public ResponseEntity<?> addRating(Integer rating, Integer userId) {
        Optional<UserTrainerModel> userTrainerModelOptional=userTrainerRepo.findByUserId(userId);
        if (userTrainerModelOptional.isPresent()){
            UserTrainerModel userTrainerModel1=userTrainerModelOptional.get();
            if (userTrainerModel1.getRating() != null) {
                return new ResponseEntity<>("You have already rated this trainer.", HttpStatus.CONFLICT);
            }
            userTrainerModel1.setRating(rating);
            userTrainerModel1.setCreatedAt(LocalDateTime.now());
            userTrainerRepo.save(userTrainerModel1);
            return new ResponseEntity<>(userTrainerModel1,HttpStatus.OK);
        }
        return new ResponseEntity<>("User Not Found",HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<?> showRating(Integer trainerId) {
        List<UserTrainerModel> userTrainerModelList = userTrainerRepo.findByTrainerId(trainerId);
        int sum = 0;
        int count = 0;
        if (!userTrainerModelList.isEmpty()) {
            for (UserTrainerModel userTrainerModel : userTrainerModelList) {
                Integer rating = userTrainerModel.getRating();
                if (rating != null) {
                    sum =sum+ rating;
                    count++;
                }
            }
            if (count == 0) {
                return ResponseEntity.ok("This trainer has not been rated yet.");
            }

            double average = (double) sum / count;
            return ResponseEntity.ok(average);


        }
        return new ResponseEntity("No trainer found.",HttpStatus.NOT_FOUND);
    }
}
