package com.WorkoutTracker.Payment;

import com.WorkoutTracker.Models.User.UserModel;
import com.WorkoutTracker.Models.User.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.Utils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;

import java.time.LocalDateTime;
import java.util.Optional;


@Service
public class PaymentService {

    @Autowired
    private UserRepo userRepo;

    @Value("${razorpay.key_id}")
    private String keyId;

    @Value("${razorpay.key_secret}")
    private String keySecret;

    private final PaymentRepo paymentRepository;

    public PaymentService(PaymentRepo paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    public Order createOrder(PaymentRequestDto request) throws Exception {
        RazorpayClient razorpay = new RazorpayClient(keyId, keySecret);

        JSONObject options = new JSONObject();
        options.put("amount", request.getAmount() * 100);
        options.put("currency", "INR");
        options.put("receipt", "receipt_" + System.currentTimeMillis());

        Order order = razorpay.orders.create(options);

        PaymentModel payment = new PaymentModel();
        payment.setUser_id(request.getUser_id());
        payment.setTrainer_id(request.getTrainer_id());
        payment.setChart_id(request.getChartId());
        payment.setAmount(request.getAmount());
        payment.setPayment_date(LocalDateTime.now());
        payment.setRazorpayOrderId(order.get("id"));
        payment.setStatus("CREATED");

        paymentRepository.save(payment);

        return order;
    }

    public boolean verifySignature(String orderId, String paymentId, String signature) throws Exception {
        String data = orderId + "|" + paymentId;
        return Utils.verifySignature(data, signature, keySecret);
    }

    public void updatePaymentStatus(String orderId, String paymentId, String signature, boolean isValid) {
        PaymentModel payment = paymentRepository.findByRazorpayOrderId(orderId);
        if (payment != null) {
            payment.setRazorpay_payment_id(paymentId);
            payment.setRazorpay_signature(signature);
            payment.setStatus(isValid ? "SUCCESS" : "FAILED");

            if (isValid) {
                LocalDateTime now = LocalDateTime.now();
                payment.setSubscriptionStart(now);
                payment.setSubscriptionEnd(now.plusMonths(1));
                Optional<UserModel> userModelOptional=userRepo.findById(payment.getUser_id());
                if(userModelOptional.isPresent()){
                    UserModel userModel=userModelOptional.get();
                    userModel.setPaymentStatus(1);
                    userRepo.save(userModel);
                }

            }

            paymentRepository.save(payment);
        }
    }

}
