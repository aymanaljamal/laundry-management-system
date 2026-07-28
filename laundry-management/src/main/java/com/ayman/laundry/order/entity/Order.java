package com.ayman.laundry.order.entity;


import com.ayman.laundry.common.entity.BaseEntity;
import com.ayman.laundry.customer.entity.Customer;
import com.ayman.laundry.employee.entity.Employee;
import com.ayman.laundry.invoice.entity.Invoice;
import com.ayman.laundry.order.enums.OrderStatus;
import com.ayman.laundry.order.enums.PaymentStatus;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(
        name = "orders",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uk_order_number",
                        columnNames = "order_number"
                )
        }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Order extends BaseEntity {


    // ===========================
    // Customer Relation
    // ===========================

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "customer_id",
            nullable = false
    )
    private Customer customer;



    // ===========================
    // Employee Relation
    // ===========================

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "assigned_employee_id"
    )
    private Employee assignedEmployee;



    // ===========================
    // Order Information
    // ===========================

    @Column(
            name = "order_number",
            nullable = false,
            length = 50
    )
    private String orderNumber;



    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @Builder.Default
    private OrderStatus status = OrderStatus.PENDING;



    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @Builder.Default
    private PaymentStatus paymentStatus = PaymentStatus.UNPAID;



    // ===========================
    // Pricing
    // ===========================

    @Column(
            nullable = false,
            precision = 10,
            scale = 2
    )
    @Builder.Default
    private BigDecimal totalAmount = BigDecimal.ZERO;



    @Column(
            precision = 10,
            scale = 2
    )
    @Builder.Default
    private BigDecimal paidAmount = BigDecimal.ZERO;



    // ===========================
    // Dates
    // ===========================

    private LocalDateTime receivedAt;


    private LocalDateTime completedAt;


    private LocalDateTime deliveredAt;



    // ===========================
    // Pickup & Delivery
    // ===========================

    @Column(length = 200)
    private String pickupAddress;


    private LocalDateTime pickupDate;



    @Column(nullable = false)
    @Builder.Default
    private Boolean requiresDelivery = false;



    // ===========================
    // Items
    // ===========================

    @OneToMany(
            mappedBy = "order",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @Builder.Default
    private List<OrderItem> items = new ArrayList<>();

    @OneToOne(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Invoice invoice;



    // ===========================
    // Helper Methods
    // ===========================


    public void addItem(OrderItem item){

        items.add(item);
        item.setOrder(this);

    }



    public void removeItem(OrderItem item){

        items.remove(item);
        item.setOrder(null);

    }



    public void calculateTotal(){

        totalAmount = items.stream()
                .filter(item -> item.getTotalPrice() != null)
                .map(OrderItem::getTotalPrice)
                .reduce(
                        BigDecimal.ZERO,
                        BigDecimal::add
                );

    }

}