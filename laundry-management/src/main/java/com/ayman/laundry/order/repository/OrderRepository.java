package com.ayman.laundry.order.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ayman.laundry.order.entity.Order;
import com.ayman.laundry.order.enums.OrderStatus;
import com.ayman.laundry.order.enums.PaymentStatus;

import java.util.List;
import java.util.Optional;


@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {


    // Find order by order number
    Optional<Order> findByOrderNumber(String orderNumber);



    // Check duplicate order number
    boolean existsByOrderNumber(String orderNumber);



    // Get customer orders
    List<Order> findByCustomerId(Long customerId);



    // Get employee assigned orders
    List<Order> findByAssignedEmployeeId(Long employeeId);



    // Filter by order status
    List<Order> findByStatus(OrderStatus status);



    // Filter by payment status
    List<Order> findByPaymentStatus(PaymentStatus paymentStatus);



    // Delivery orders
    List<Order> findByRequiresDeliveryTrue();


}