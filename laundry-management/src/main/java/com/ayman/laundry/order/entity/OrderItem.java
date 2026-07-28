package com.ayman.laundry.order.entity;


import com.ayman.laundry.cloth.entity.Cloth;
import com.ayman.laundry.common.entity.BaseEntity;
import com.ayman.laundry.service.entity.LaundryService;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;


@Entity
@Table(name = "order_items")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderItem extends BaseEntity {


    // ===========================
    // Order Relation
    // ===========================

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "order_id",
            nullable = false
    )
    private Order order;



    // ===========================
    // Cloth Relation
    // ===========================

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "cloth_id",
            nullable = false
    )
    private Cloth cloth;



    // ===========================
    // Service Relation
    // ===========================

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "service_id",
            nullable = false
    )
    private LaundryService service;



    // ===========================
    // Quantity & Pricing
    // ===========================

    @Column(nullable = false)
    private Integer quantity;



    @Column(
            nullable = false,
            precision = 10,
            scale = 2
    )
    private BigDecimal unitPrice;



    @Column(
            nullable = false,
            precision = 10,
            scale = 2
    )
    private BigDecimal totalPrice;



    // ===========================
    // Notes
    // ===========================

    @Column(length = 500)
    private String notes;



    // ===========================
    // Helper Method
    // ===========================


    public void calculateTotalPrice(){

        if(unitPrice != null && quantity != null){

            this.totalPrice =
                    unitPrice.multiply(
                            BigDecimal.valueOf(quantity)
                    );

        }

    }

}