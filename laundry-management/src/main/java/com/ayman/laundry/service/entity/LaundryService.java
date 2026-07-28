package com.ayman.laundry.service.entity;


import com.ayman.laundry.common.entity.BaseEntity;
import com.ayman.laundry.service.enums.ServiceType;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;



@Entity
@Table(
        name = "laundry_services",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uk_service_name",
                        columnNames = "name"
                )
        }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LaundryService extends BaseEntity {



    // ===========================
    // Service Information
    // ===========================


    @Column(
            nullable = false,
            length = 100
    )
    private String name;



    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ServiceType type;



    @Column(length = 500)
    private String description;



    // ===========================
    // Pricing
    // ===========================


    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal price;



    // ===========================
    // Status
    // ===========================


    @Column(nullable = false)
    @Builder.Default
    private Boolean active = true;



    public void deactivate(){

        this.active = false;

    }


    public void activate(){

        this.active = true;

    }


}