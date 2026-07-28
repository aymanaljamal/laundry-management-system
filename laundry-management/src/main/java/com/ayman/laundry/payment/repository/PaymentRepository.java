package com.ayman.laundry.payment.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ayman.laundry.payment.entity.Payment;
import com.ayman.laundry.payment.enums.PaymentStatus;

import java.util.List;


@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {


    // Get all payments for order
    List<Payment> findByOrderId(Long orderId);



    // Get payments by status
    List<Payment> findByStatus(PaymentStatus status);



    // Get order payments by status
    List<Payment> findByOrderIdAndStatus(
            Long orderId,
            PaymentStatus status
    );


}