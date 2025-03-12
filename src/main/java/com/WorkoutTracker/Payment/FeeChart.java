package com.WorkoutTracker.Payment;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "fee_chart_table")
@Data
public class FeeChart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "chart_id")
    private Integer chart_id;

    @Column(name = "amount")
    private double amount;

    @Column(name = "fee_category")
    private String fee_category;

    public Integer getChart_id() {
        return chart_id;
    }

    public void setChart_id(Integer chart_id) {
        this.chart_id = chart_id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getFee_category() {
        return fee_category;
    }

    public void setFee_category(String fee_category) {
        this.fee_category = fee_category;
    }
}
