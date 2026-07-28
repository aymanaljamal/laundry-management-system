package com.ayman.laundry.delivery.entity;


import com.ayman.laundry.common.entity.BaseEntity;
import com.ayman.laundry.delivery.enums.*;
import com.ayman.laundry.employee.entity.Employee;
import com.ayman.laundry.order.entity.Order;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;



@Entity
@Table(name = "deliveries")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Delivery extends BaseEntity {



    // ===========================
    // Order Relation
    // ===========================

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "order_id",
            nullable = false,
            unique = true
    )
    private Order order;



    // ===========================
    // Delivery Employee
    // ===========================

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "delivery_employee_id"
    )
    private Employee deliveryEmployee;



    // ===========================
    // Delivery Information
    // ===========================

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private DeliveryType type;



    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @Builder.Default
    private DeliveryStatus status = DeliveryStatus.PENDING;



    @Column(length = 200)
    private String deliveryAddress;



    @Column(length = 20)
    private String customerPhone;



    private LocalDateTime pickupTime;


    private LocalDateTime deliveredTime;



    // ===========================
    // Methods
    // ===========================


    public void markDelivered(){

        this.status = DeliveryStatus.DELIVERED;
        this.deliveredTime = LocalDateTime.now();

    }


}