//package com.WorkoutTracker.Payment;
//
//import com.razorpay.Order;
//import com.razorpay.RazorpayClient;
//import com.razorpay.RazorpayException;
//import org.json.JSONObject;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.HashMap;
//import java.util.Map;
//import java.util.UUID;
//
//@RestController
//@RequestMapping("/api/payment")
//public class PaymentController {
//
//    @PostMapping("/createOrder")
//    public ResponseEntity<?> createOrder(@RequestBody Map<String, Object> data) {
//        try {
//            // Convert amount to integer
//            int amount = (int) data.get("amount");
//
//            // Initialize Razorpay client with your API keys
//            RazorpayClient razorpay = new RazorpayClient("YOUR_KEY_ID", "YOUR_KEY_SECRET");
//
//            // Create order request JSON
//            JSONObject orderRequest = new JSONObject();
//            orderRequest.put("amount", amount * 100); // Amount in paise
//            orderRequest.put("currency", "INR");
//            orderRequest.put("receipt", "txn_" + UUID.randomUUID());
//
//            // Create the order
//            Order order = razorpay.orders.create(orderRequest);
//
//            // Build response to send to frontend
//            Map<String, Object> response = new HashMap<>();
//            response.put("id", order.get("id"));
//            response.put("currency", order.get("currency"));
//            response.put("amount", order.get("amount"));
//
//            return ResponseEntity.ok(response);
//        } catch (RazorpayException e) {
//            e.printStackTrace();
//            return ResponseEntity.status(500).body("Error creating order: " + e.getMessage());
//        } catch (Exception e) {
//            e.printStackTrace();
//            return ResponseEntity.status(500).body("Unexpected error: " + e.getMessage());
//        }
//    }
//}
