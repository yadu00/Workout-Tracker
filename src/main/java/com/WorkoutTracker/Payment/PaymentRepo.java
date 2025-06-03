package com.WorkoutTracker.Payment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface PaymentRepo extends JpaRepository<PaymentModel,Integer> {
    PaymentModel findByRazorpayOrderId(String razorpay_order_id);

    @Query("SELECT ut FROM PaymentModel ut WHERE ut.user_id = :userId And ut.status =:status")
    Optional<PaymentModel> findByUser_IdAndStatus(Integer userId,String status);

}
