package com.ayman.laundry.tailoring.entity;

import com.ayman.laundry.common.entity.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "measurements")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Measurement extends BaseEntity {

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tailoring_order_id", nullable = false, unique = true)
    private TailoringOrder tailoringOrder;

    @Column(nullable = false)
    private Double chest;

    @Column(nullable = false)
    private Double waist;

    @Column(nullable = false)
    private Double shoulder;

    @Column(nullable = false)
    private Double sleeveLength;

    @Column(nullable = false)
    private Double height;

    @Column(length = 2000)
    private String notes;
}
