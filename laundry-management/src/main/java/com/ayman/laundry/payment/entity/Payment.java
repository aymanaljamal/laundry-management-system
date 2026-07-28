package com.ayman.laundry.payment.entity;


import com.ayman.laundry.common.entity.BaseEntity;
import com.ayman.laundry.order.entity.Order;
import com.ayman.laundry.payment.enums.PaymentMethod;
import com.ayman.laundry.payment.enums.PaymentStatus;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;



@Entity
@Table(name = "payments")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Payment extends BaseEntity {


    // ===========================
    // Relation With Order
    // ===========================


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "order_id",
            nullable = false
    )
    private Order order;



    // ===========================
    // Payment Information
    // ===========================


    @Column(
            nullable = false,
            precision = 10,
            scale = 2
    )
    private BigDecimal amount;



    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PaymentMethod method;



    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @Builder.Default
    private PaymentStatus status = PaymentStatus.PENDING;



    private LocalDateTime paidAt;



    // ===========================
    // Helper Methods
    // ===========================


    public void completePayment(){

        this.status = PaymentStatus.COMPLETED;
        this.paidAt = LocalDateTime.now();

    }


    public void refundPayment(){

        this.status = PaymentStatus.REFUNDED;

    }

}