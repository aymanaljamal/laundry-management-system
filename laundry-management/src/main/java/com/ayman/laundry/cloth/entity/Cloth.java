package com.ayman.laundry.cloth.entity;

import java.math.BigDecimal;

import com.ayman.laundry.cloth.enums.ClothCategory;
import com.ayman.laundry.common.entity.BaseEntity;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(
        name = "cloths",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uk_cloth_name",
                        columnNames = "name"
                )
        }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Cloth extends BaseEntity {


    // ===========================
    // Basic Information
    // ===========================


    @Column(
            nullable = false,
            length = 100
    )
    private String name;



    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ClothCategory category;



    // ===========================
    // Pricing
    // ===========================


    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal basePrice;



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