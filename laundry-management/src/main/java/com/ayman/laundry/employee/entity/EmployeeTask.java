package com.ayman.laundry.employee.entity;

import java.time.LocalDateTime;

import com.ayman.laundry.common.entity.BaseEntity;
import com.ayman.laundry.employee.enums.EmployeeTaskStatus;
import com.ayman.laundry.employee.enums.EmployeeTaskType;
import com.ayman.laundry.order.entity.Order;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "employee_tasks")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmployeeTask extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EmployeeTaskType taskType;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @Builder.Default
    private EmployeeTaskStatus status = EmployeeTaskStatus.PENDING;

    private LocalDateTime startedAt;

    private LocalDateTime completedAt;

    @Column(length = 1000)
    private String notes;

    public void startTask() {
        this.status = EmployeeTaskStatus.IN_PROGRESS;
        if (this.startedAt == null) {
            this.startedAt = LocalDateTime.now();
        }
    }

    public void completeTask() {
        this.status = EmployeeTaskStatus.COMPLETED;
        this.completedAt = LocalDateTime.now();
    }

    public void cancelTask() {
        this.status = EmployeeTaskStatus.CANCELLED;
        this.completedAt = LocalDateTime.now();
    }
}
