package com.ayman.laundry.tailoring.entity;

import java.math.BigDecimal;

import com.ayman.laundry.common.entity.BaseEntity;
import com.ayman.laundry.tailoring.enums.AlterationType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "alterations")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Alteration extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tailoring_order_id", nullable = false)
    private TailoringOrder tailoringOrder;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AlterationType type;

    @Column(length = 2000)
    private String description;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal price;
}
