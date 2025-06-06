package com.WorkoutTracker.Payment;

import com.WorkoutTracker.Model.Bmi.BmiModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PaymentRepo extends JpaRepository<PaymentModel,Integer> {
    PaymentModel findByRazorpayOrderId(String razorpay_order_id);

    @Query("SELECT ut FROM PaymentModel ut WHERE ut.user_id = :userId And ut.status =:status")
    Optional<PaymentModel> findByUser_IdAndPaymentStatus(Integer userId, String status);

    @Query("SELECT ut FROM PaymentModel ut WHERE ut.user_id = :userId")
    List<PaymentModel> findByUser_Id(Integer userId);

    @Query("SELECT ut FROM PaymentModel ut WHERE ut.user_id = :userId And ut.status =:status")
    List<PaymentModel> findByUser_IdAndStatus(Integer userId,String status);

    @Query("SELECT p FROM PaymentModel p WHERE p.user_id = :userId And p.status =:status ORDER BY p.payment_date DESC LIMIT 1")
    Optional<PaymentModel> findByLatestPaymentByUser_Id(@Param("userId") Integer userId,String status);


}
