package com.WorkoutTracker.Service;

import com.WorkoutTracker.Dto.*;

import com.WorkoutTracker.Model.WorkoutDay.WorkoutDayModal;
import com.WorkoutTracker.Model.WorkoutDay.WorkoutDayRepo;
import com.WorkoutTracker.Model.ExerciseDetails.ExcerciseDetailsModel;
import com.WorkoutTracker.Model.ExerciseDetails.ExcerciseDetailsRepo;
import com.WorkoutTracker.Model.ExerciseSpecialization.ExcerciseSpecializationModel;
import com.WorkoutTracker.Model.ExerciseSpecialization.ExcerciseSpecializationRepo;
import com.WorkoutTracker.Model.Gender.GenderModel;
import com.WorkoutTracker.Model.Gender.GenderRepo;
import com.WorkoutTracker.Model.Trainer.TrainerModel;

import com.WorkoutTracker.Model.Trainer.TrainerRepo;

import com.WorkoutTracker.Model.User.UserModel;
import com.WorkoutTracker.Model.User.UserRepo;
import com.WorkoutTracker.Model.UserTrainer.UserTrainerModel;
import com.WorkoutTracker.Model.UserTrainer.UserTrainerRepo;

import com.WorkoutTracker.Model.Bmi.BmiModel;
import com.WorkoutTracker.Model.Bmi.BmiRepo;
import com.WorkoutTracker.Model.Workouts.WorkoutModel;
import com.WorkoutTracker.Model.Workouts.WorkoutRepo;
import com.WorkoutTracker.Payment.PaymentModel;
import com.WorkoutTracker.Payment.PaymentRepo;
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
    private BmiRepo bmiRepo;

    @Autowired
    private WorkoutDayRepo workoutDayRepo;

    @Autowired
    private PaymentRepo paymentRepo;


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
            UserModel userModel = userModelOptional.get();
            boolean firstLogin = Boolean.TRUE.equals(userModel.getIsFirstLogin());
            if (firstLogin) {
                userModel.setIsFirstLogin(false);
                userRepo.save(userModel);
            }
            Integer user_id = userModel.getUser_id();
            Map<String, Object> loginres = new HashMap<>();
            loginres.put("message", "Login success");
            loginres.put("user_id", user_id);
            loginres.put("firstLogin", firstLogin);
            return new ResponseEntity<>(loginres, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("User Credentials Incorrect", HttpStatus.NOT_FOUND);
        }
    }


    //select specializations
    public ResponseEntity<List<ExcerciseSpecializationModel>> getAllSpecialization() {
        List<ExcerciseSpecializationModel> excerciseSpecializationModels = excerciseSpecializationRepo.findAll();
        if (!excerciseSpecializationModels.isEmpty()) {
            return new ResponseEntity<>(excerciseSpecializationModels, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }


    //list trainers according to specialization
    public ResponseEntity<List<TrainerDto>> filterTrainers(Integer specialization_id) {
        List<TrainerDto> dto = new ArrayList<>();
        List<TrainerModel> trainerModels = trainerRepo.findBySpecializationIdAndStatusID(specialization_id,2);
        if (!trainerModels.isEmpty()) {
            for (TrainerModel trainerModel : trainerModels) {
                    TrainerDto trainerDto1 = new TrainerDto();
                    trainerDto1.setName(trainerModel.getName());
                    trainerDto1.setCertification(trainerModel.getCertification());
                    trainerDto1.setSpecialization(trainerModel.getSpecialization_id());
                    trainerDto1.setExperienceYears(trainerModel.getExperienceYears());
                    trainerDto1.setSalary(trainerModel.getSalary());
                    trainerDto1.setTrainer_id(trainerModel.getTrainer_id());
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




    public ResponseEntity<List<TrainerDto>> getallTrainers() {
        List<TrainerDto> dto = new ArrayList<>();
        List<TrainerModel> trainerModels = trainerRepo.findByStatusID(2);

        for (TrainerModel trainerModel : trainerModels) {
            TrainerDto trainerDto1 = new TrainerDto();
            trainerDto1.setTrainer_id(trainerModel.getTrainer_id());
            trainerDto1.setName(trainerModel.getName());
            trainerDto1.setCertification(trainerModel.getCertification());
            trainerDto1.setExperienceYears(trainerModel.getExperienceYears());
            trainerDto1.setSalary(trainerModel.getSalary());

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







    public ResponseEntity<?> viewWorkouts(Integer userId,Integer workoutdayId) {
        List<WorkoutModel> workoutModelList = workoutRepo.findByUserIdAndId(userId,workoutdayId);
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
                    workoutDto.setFocusarea(excerciseDetailsModel.getFocusarea());
                    workoutDto.setExerciseImage(excerciseDetailsModel.getExerciseImage());

                }
                Optional<WorkoutDayModal> dailyWorkoutOptional = workoutDayRepo.findById(workoutModel.getWorkoutdayId());
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
            dailyWorkoutDto.setWorkoutdayId(workoutDayModal.getWorkoutdayId());
            dailyWorkoutDto.setStatus(workoutDayModal.getStatus());
            dtoList.add(dailyWorkoutDto);
        }

        return new ResponseEntity<>(dtoList, HttpStatus.OK);
    }
    public ResponseEntity<?> gettodaysWorkout(Integer userId,LocalDate date) {
        Optional<WorkoutDayModal> dailyWorkoutOptional = workoutDayRepo.findByUser_IdAndDate(userId,date);
        if (dailyWorkoutOptional.isPresent()){
            return new ResponseEntity<>(dailyWorkoutOptional,HttpStatus.OK);
        }

        return new ResponseEntity<>("not found", HttpStatus.NOT_FOUND);
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
                trainerDto.setTrainer_id(trainerModel.getTrainer_id());
                trainerDto.setEmail(trainerModel.getEmail());
                trainerDto.setExperienceYears(trainerModel.getExperienceYears());
                trainerDto.setCertification(trainerModel.getCertification());
                trainerDto.setSalary(trainerModel.getSalary());
                trainerDto.setMobile(trainerModel.getMobile());

                return new ResponseEntity<>(trainerDto, HttpStatus.OK); // Return a single object
            }
        }
        return new ResponseEntity<>("Trainer not found", HttpStatus.NOT_FOUND);
    }


    public ResponseEntity<?> ViewTrainer(Integer trainer_id) {
        Optional<TrainerModel> trainerModelOptional = trainerRepo.findById(trainer_id);
        if (trainerModelOptional.isPresent()) {
            TrainerModel trainerModel = trainerModelOptional.get();
                TrainerDto trainerDto = new TrainerDto();
                trainerDto.setName(trainerModel.getName());
                trainerDto.setEmail(trainerModel.getEmail());
                trainerDto.setExperienceYears(trainerModel.getExperienceYears());
                trainerDto.setCertification(trainerModel.getCertification());
                trainerDto.setSalary(trainerModel.getSalary());
                trainerDto.setMobile(trainerModel.getMobile());
                return new ResponseEntity<>(trainerDto, HttpStatus.OK);
            }

        return new ResponseEntity<>("Trainer not found", HttpStatus.NOT_FOUND);
    }




    public ResponseEntity<?> logWorkoutStatus(Integer workout_id,WorkoutUpdateDto workoutUpdateDto) {
        Optional<WorkoutModel> workoutModelOptional = workoutRepo.findById(workout_id);
        if (workoutModelOptional.isPresent()) {
            WorkoutModel workoutModel = workoutModelOptional.get();
            workoutModel.setSetsDone(workoutUpdateDto.getSetsDone());
            workoutModel.setRepsDone(workoutUpdateDto.getRepsDone());
            workoutModel.setWorkoutStatus(workoutUpdateDto.getWorkoutStatus());
            workoutModel.setRemarks(workoutUpdateDto.getRemarks());
            workoutModel.setStatus(2);
            workoutRepo.save(workoutModel);
            boolean allLogged = true;
            Integer dailyWorkoutId = workoutModel.getWorkoutdayId();
            List<WorkoutModel> workoutModelList=workoutRepo.findByDailyWorkoutId(dailyWorkoutId);
            for (WorkoutModel wm : workoutModelList) {
                if (wm.getStatus() != 2) {
                    allLogged = false;
                    break;
                }
            }
            if (allLogged) {
                Optional<WorkoutDayModal> workoutDayModalOptional = workoutDayRepo.findById(dailyWorkoutId);
                if (workoutDayModalOptional.isPresent()) {
                    WorkoutDayModal workoutDayModal = workoutDayModalOptional.get();
                    workoutDayModal.setStatus(2);
                    workoutDayRepo.save(workoutDayModal);
                }
            }
            return new ResponseEntity<>(workoutModel, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Workout Not found", HttpStatus.NOT_FOUND);
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

    public ResponseEntity<?> viewTodaysExercise(Integer userId, LocalDate date) {
        List<WorkoutDayModal> workoutDayModalList = workoutDayRepo.findByUser_IdDate(userId, date);
        List<WorkoutDto> dtos = new ArrayList<>();
        if (!workoutDayModalList.isEmpty()) {
            for (WorkoutDayModal workoutDayModal : workoutDayModalList) {

                List<WorkoutModel> results = workoutRepo.findAllByIdColumn(workoutDayModal.getWorkoutdayId());
                if (!results.isEmpty()){
                    for(WorkoutModel workoutModel: results){
                        WorkoutDto workoutDto = new WorkoutDto();
                        workoutDto.setSets(workoutModel.getSets());
                        workoutDto.setReps(workoutModel.getReps());
                        workoutDto.setWeights(workoutModel.getWeights());
                        Optional<ExcerciseDetailsModel> excerciseDetailsModelOptional = excerciseDetailsRepo.findById(workoutModel.getExercise_id());
                        if (excerciseDetailsModelOptional.isPresent()) {
                            ExcerciseDetailsModel excerciseDetailsModel = excerciseDetailsModelOptional.get();
                            workoutDto.setExcercise_name(excerciseDetailsModel.getExercise_name());
                            workoutDto.setExerciseImage(excerciseDetailsModel.getExerciseImage());

                        }
                        dtos.add(workoutDto);
                    }

                }

            }
            return new ResponseEntity<>(dtos, HttpStatus.OK);
        }

        return new ResponseEntity<>("No workouts found", HttpStatus.NOT_FOUND);

    }

    public ResponseEntity<?> logBmi(BmiModel bmiModel, Integer user_id) {
        BmiModel bmiModel1 =new BmiModel();
        bmiModel1.setUser_id(user_id);
        bmiModel1.setWeight(bmiModel.getWeight());
        bmiModel1.setLogDate(LocalDate.now());
        bmiModel1.setBmi(bmiModel.getBmi());
        bmiModel1.setHeight(bmiModel.getHeight());
        bmiRepo.save(bmiModel1);
        return new ResponseEntity<>(bmiModel1, HttpStatus.OK);
    }

    public ResponseEntity<?> BmiHistory(Integer userId) {
        List<BmiModel> bmiModels = bmiRepo.findAllByUser_Id(userId);

        if (bmiModels != null && !bmiModels.isEmpty()) {
            return new ResponseEntity<>(bmiModels, HttpStatus.OK);
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

    public ResponseEntity<?> viewPaymentPlan(Integer userId) {
        Optional<UserTrainerModel> userTrainerModelOptional=userTrainerRepo.findByUserId(userId);
        if (userTrainerModelOptional.isPresent()){
            UserTrainerModel userTrainerModel=userTrainerModelOptional.get();
            Optional<TrainerModel> trainerModelOptional=trainerRepo.findById(userTrainerModel.getTrainer_id());
            if (trainerModelOptional.isPresent()){
                TrainerModel trainerModel=trainerModelOptional.get();
                TrainerDto trainerDto=new TrainerDto();
                trainerDto.setSalary(trainerModel.getSalary());
                return new ResponseEntity<>(trainerDto,HttpStatus.OK);
            }


        }
        return new ResponseEntity<>("not found",HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<?> getPaymentStatus(Integer userId) {
        Optional<UserModel> userModelOptional = userRepo.findById(userId);
        String status="SUCCESS";
        if (userModelOptional.isPresent()) {
            UserModel userModel = userModelOptional.get();
            Optional<PaymentModel> paymentModelOptional = paymentRepo.findByLatestPaymentByUser_Id(userModel.getUser_id(),status);
            if (paymentModelOptional.isPresent()) {
                UserDto userDto = new UserDto();
                PaymentModel paymentModel = paymentModelOptional.get();
                userDto.setSubscriptionStart(paymentModel.getSubscriptionStart());
                userDto.setSubscriptionEnd(paymentModel.getSubscriptionEnd());
                return new ResponseEntity<>(userDto, HttpStatus.OK);
            }

        }
        return new ResponseEntity<>("not found",HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<?> getLatestBmi(Integer userId) {
        Optional<BmiModel> bmiModelOptional = bmiRepo.findLatestBmiByUserId(userId);
        if (bmiModelOptional.isPresent()){
            BmiModel bmiModel=bmiModelOptional.get();
            return new ResponseEntity<>(bmiModel,HttpStatus.OK);
        }
    return new ResponseEntity<>("no bmi records",HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<?> getPaymentDetails(Integer userId) {
        List<PaymentDetailsDto> paymentDetailsDtos=new ArrayList<>();
        String status="SUCCESS";
        List<PaymentModel> paymentModelList=paymentRepo.findByUser_IdAndStatus(userId,status);
        if (!paymentModelList.isEmpty()){
            for (PaymentModel paymentModel:paymentModelList){
                PaymentDetailsDto paymentDetailsDto=new PaymentDetailsDto();
                paymentDetailsDto.setPayment_date(paymentModel.getPayment_date());
                paymentDetailsDto.setAmount(paymentModel.getAmount());
                paymentDetailsDto.setStatus(paymentModel.getStatus());
                paymentDetailsDto.setSubscriptionEnd(paymentModel.getSubscriptionEnd());
                paymentDetailsDto.setSubscriptionStart(paymentModel.getSubscriptionStart());
                paymentDetailsDtos.add(paymentDetailsDto);
            }
            return new ResponseEntity<>(paymentDetailsDtos,HttpStatus.OK);
        }
        return new ResponseEntity<>("No Payment details found for this user", HttpStatus.NOT_FOUND);
    }


}
