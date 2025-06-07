package com.WorkoutTracker.Service;

import com.WorkoutTracker.Dto.PaymentDetailsDto;
import com.WorkoutTracker.Model.Admin.AdminModel;
import com.WorkoutTracker.Model.Admin.AdminRepo;
import com.WorkoutTracker.Dto.AdminLoginDto;
import com.WorkoutTracker.Dto.TrainerDto;
import com.WorkoutTracker.Dto.UserDto;
import com.WorkoutTracker.Model.ExerciseSpecialization.ExcerciseSpecializationModel;
import com.WorkoutTracker.Model.ExerciseSpecialization.ExcerciseSpecializationRepo;
import com.WorkoutTracker.Model.Gender.GenderModel;
import com.WorkoutTracker.Model.Gender.GenderRepo;
import com.WorkoutTracker.Model.TrainerAccountStatus.StatusModel;
import com.WorkoutTracker.Model.TrainerAccountStatus.StatusRepo;
import com.WorkoutTracker.Model.Trainer.TrainerModel;
import com.WorkoutTracker.Model.User.UserModel;
import com.WorkoutTracker.Model.Trainer.TrainerRepo;
import com.WorkoutTracker.Model.User.UserRepo;

import com.WorkoutTracker.Payment.PaymentModel;
import com.WorkoutTracker.Payment.PaymentRepo;
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
    private StatusRepo statusRepo;

    @Autowired
    private PaymentRepo paymentRepo;


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
                trainerDto1.setSalary(trainerModel.getSalary());
                trainerDto1.setCertificationImage(trainerModel.getCertificationImage());
                Optional<ExcerciseSpecializationModel>excerciseSpecializationModelOptional=excerciseSpecializationRepo.findById(trainerModel.getSpecialization_id());
                if (excerciseSpecializationModelOptional.isPresent()){
                    trainerDto1.setSpecialisationName(excerciseSpecializationModelOptional.get().getSpecialization_name());
                }

                dto.add(trainerDto1);
            }

        }

        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    //add specialization for exercise
    public ResponseEntity<?> addSpecialization(ExcerciseSpecializationModel excerciseSpecializationModel) {
        ExcerciseSpecializationModel excerciseSpecializationModel1 =new ExcerciseSpecializationModel();
        excerciseSpecializationModel1.setSpecialization_name(excerciseSpecializationModel.getSpecialization_name());
        excerciseSpecializationRepo.save(excerciseSpecializationModel1);
        return new ResponseEntity<>(excerciseSpecializationModel1,HttpStatus.OK);
    }



    //add gender information
    public ResponseEntity<?> addGender(GenderModel genderModel) {
        GenderModel genderModel1 =new GenderModel();
        genderModel1.setGenderName(genderModel.getGenderName());
        genderRepo.save(genderModel1);
        return new ResponseEntity<>(genderModel1, HttpStatus.OK);
    }






    //add gender informations
    public ResponseEntity<List<GenderModel>> genders() {
            List<GenderModel> genderModels = genderRepo.findAll();

            if (genderModels.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(genderModels, HttpStatus.OK);
        }



    //list all exercise specializations
    public ResponseEntity<List<ExcerciseSpecializationModel>> specialisations() {
        List<ExcerciseSpecializationModel> excerciseSpecializationModels = excerciseSpecializationRepo.findAll();
        if (excerciseSpecializationModels.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(excerciseSpecializationModels, HttpStatus.OK);
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
            trainerDto.setCertificationImage(trainerModel.getCertificationImage());
            Optional<ExcerciseSpecializationModel> excerciseSpecialisationModel = excerciseSpecializationRepo.findById(trainerModel.getSpecialization_id());
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

    //list all approved trainers
    public ResponseEntity<List<TrainerDto>> viewApprovedTrainers() {
        List<TrainerDto> dto = new ArrayList<>();
        List<TrainerModel> trainerModels = trainerRepo.findByStatusID(2);
        if (!trainerModels.isEmpty()) {
            for (TrainerModel trainerModel : trainerModels) {
                TrainerDto trainerDto1 = new TrainerDto();
                trainerDto1.setTrainer_id(trainerModel.getTrainer_id());
                trainerDto1.setName(trainerModel.getName());
                trainerDto1.setEmail(trainerModel.getEmail());
                trainerDto1.setCertification(trainerModel.getCertification());
                trainerDto1.setExperienceYears(trainerModel.getExperienceYears());
                trainerDto1.setSalary(trainerModel.getSalary());
                trainerDto1.setCertificationImage(trainerModel.getCertificationImage());
                Optional<ExcerciseSpecializationModel>excerciseSpecializationModelOptional=excerciseSpecializationRepo.findById(trainerModel.getSpecialization_id());
                if (excerciseSpecializationModelOptional.isPresent()){
                    trainerDto1.setSpecialisationName(excerciseSpecializationModelOptional.get().getSpecialization_name());
                }
                dto.add(trainerDto1);
            }

        }

        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    public ResponseEntity<List<PaymentDetailsDto>> viewPaymentDetails() {
        List<PaymentDetailsDto>paymentDetailsDtos=new ArrayList<>();
        List<PaymentModel>paymentModelList=paymentRepo.findAll();
        if (!paymentModelList.isEmpty()){
            for (PaymentModel paymentModel:paymentModelList) {
                PaymentDetailsDto paymentDetailsDto = new PaymentDetailsDto();
                Optional<UserModel> userModelOptional = userRepo.findById(paymentModel.getUser_id());
                if (userModelOptional.isPresent()) {
                    UserModel userModel = userModelOptional.get();
                    if (userModel.getName() == null) {
                        continue;
                    }


                    paymentDetailsDto.setUser(userModelOptional.get().getName());
                    paymentDetailsDto.setPayment_date(paymentModel.getPayment_date());
                    paymentDetailsDto.setRazorpay_payment_id(paymentModel.getRazorpay_payment_id());
                    paymentDetailsDto.setAmount(paymentModel.getAmount());
                    paymentDetailsDto.setStatus(paymentModel.getStatus());
                    paymentDetailsDto.setSubscriptionStart(paymentModel.getSubscriptionStart());

                    Optional<TrainerModel> trainerModelOptional = trainerRepo.findById(paymentModel.getTrainer_id());
                    if (trainerModelOptional.isPresent()) {
                        paymentDetailsDto.setTrainer(trainerModelOptional.get().getName());
                    }
                    paymentDetailsDtos.add(paymentDetailsDto);
                }
            }

        }
        return new ResponseEntity<>(paymentDetailsDtos, HttpStatus.OK);
    }

    public ResponseEntity<?> viewGender() {
        List<GenderModel> genderModelList=genderRepo.findAll();
        if (!genderModelList.isEmpty()){
            return new ResponseEntity<>(genderModelList, HttpStatus.OK);

        }
        return new ResponseEntity<>("No gender records found",HttpStatus.NOT_FOUND);

    }
}




