package com.WorkoutTracker.Payment;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "payment_table")
@Data
public class PaymentModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "payment_id")
    private Integer payment_id;

    @Column(name = "user_id")
    private Integer user_id;

    @Column(name = "amount")
    private double amount;

    @Column(name = "payment_date")
    private LocalDateTime payment_date;

    @Column(name = "chart_id")
    private Integer chart_id;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPayment_id() {
        return payment_id;
    }

    public void setPayment_id(Integer payment_id) {
        this.payment_id = payment_id;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public LocalDateTime getPayment_date() {
        return payment_date;
    }

    public void setPayment_date(LocalDateTime payment_date) {
        this.payment_date = payment_date;
    }

    public Integer getChart_id() {
        return chart_id;
    }

    public void setChart_id(Integer chart_id) {
        this.chart_id = chart_id;
    }
}
