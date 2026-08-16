package com.ayman.laundry.tailoring.entity;

import com.ayman.laundry.common.entity.BaseEntity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "measurements")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Measurement extends BaseEntity {

    @ManyToOne(
            fetch = FetchType.LAZY,
            optional = false
    )
    @JoinColumn(
            name = "tailoring_order_id",
            nullable = false
    )
    private TailoringOrder tailoringOrder;

    @Column(
            name = "chest",
            nullable = false
    )
    @Setter
    private Double chest;

    @Column(
            name = "waist",
            nullable = false
    )
    @Setter
    private Double waist;

    @Column(
            name = "shoulder",
            nullable = false
    )
    @Setter
    private Double shoulder;

    @Column(
            name = "sleeve_length",
            nullable = false
    )
    @Setter
    private Double sleeveLength;

    @Column(
            name = "height",
            nullable = false
    )
    @Setter
    private Double height;

    @Column(
            name = "notes",
            length = 2000
    )
    @Setter
    private String notes;

    public void setTailoringOrder(
            TailoringOrder tailoringOrder
    ) {
        this.tailoringOrder = tailoringOrder;
    }


    
}