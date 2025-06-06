package com.WorkoutTracker.Model.TrainerAccountStatus;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "Status")
@Data
public class StatusModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "statusId")
    private Integer statusId;
    @Column(name = "status")
    private String status;

    public Integer getStatusId() {
        return statusId;
    }

    public void setStatusId(Integer statusId) {
        this.statusId = statusId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
