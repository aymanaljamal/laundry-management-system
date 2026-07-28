package com.ayman.laundry.customer.entity;


import com.ayman.laundry.common.entity.BaseEntity;
import com.ayman.laundry.customer.enums.CustomerType;
import com.ayman.laundry.review.entity.Review;
import com.ayman.laundry.tailoring.entity.TailoringOrder;
import com.ayman.laundry.user.entity.User;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;



@Entity
@Table(
        name = "customers",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uk_customer_customer_number",
                        columnNames = "customer_number"
                )
        }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Customer extends BaseEntity {


    // ===========================
    // Relation With User
    // ===========================


    @OneToOne(
            fetch = FetchType.LAZY
    )
    @JoinColumn(
            name = "user_id",
            unique = true
    )
    private User user;



    // ===========================
    // Customer Information
    // ===========================


    @Column(
            name = "customer_number",
            nullable = false,
            length = 50
    )
    private String customerNumber;



    @Column(
            nullable = false,
            length = 100
    )
    private String fullName;



    @Column(
            nullable = false,
            length = 20
    )
    private String phoneNumber;



    @Column(length = 100)
    private String address;



    @Column(length = 100)
    private String city;



    // ===========================
    // Customer Type
    // ===========================


    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @Builder.Default
    private CustomerType type = CustomerType.INDIVIDUAL;



    // ===========================
    // Customer Status
    // ===========================


    @Column(nullable = false)
    @Builder.Default
    private Boolean active = true;



    // ===========================
    // Loyalty
    // ===========================


    @Column(nullable = false)
    @Builder.Default
    private Integer loyaltyPoints = 0;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<Review> reviews = new ArrayList<>();

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<TailoringOrder> tailoringOrders = new ArrayList<>();

    // ===========================
    // Helper Methods
    // ===========================


    public void deactivateCustomer(){

        this.active = false;

    }


    public void activateCustomer(){

        this.active = true;

    }


    public void addLoyaltyPoints(Integer points){

        if(points != null && points > 0){

            this.loyaltyPoints += points;

        }

    }


    public void removeLoyaltyPoints(Integer points){

        if(points != null && points > 0){

            this.loyaltyPoints -= points;

        }

    }

}