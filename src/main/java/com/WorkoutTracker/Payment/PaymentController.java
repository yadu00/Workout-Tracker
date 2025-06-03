package com.WorkoutTracker.Payment;
import com.razorpay.Order;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
@CrossOrigin
@RestController
@RequestMapping("/api/payment")
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping("/create-order")
    public String createOrder(@RequestBody PaymentRequestDto request) throws Exception {
        Order order = paymentService.createOrder(request);
        return order.toString(); // returns JSON order details
    }

    @PostMapping("/verify")
    public String verify(@RequestBody Map<String, String> payload) throws Exception {
        String orderId = payload.get("razorpay_order_id");
        String paymentId = payload.get("razorpay_payment_id");
        String signature = payload.get("razorpay_signature");

        boolean isValid = paymentService.verifySignature(orderId, paymentId, signature);
        paymentService.updatePaymentStatus(orderId, paymentId, signature, isValid);

        return isValid ? "Payment Verified" : "Payment Verification Failed";
    }
}