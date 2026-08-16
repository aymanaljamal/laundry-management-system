package com.ayman.laundry.tailoring.entity;

import com.ayman.laundry.common.entity.BaseEntity;
import com.ayman.laundry.tailoring.enums.AlterationType;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "alterations")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Alteration extends BaseEntity {

    @ManyToOne(
            fetch = FetchType.LAZY,
            optional = false
    )
    @JoinColumn(
            name = "tailoring_order_id",
            nullable = false
    )
    private TailoringOrder tailoringOrder;

    @Enumerated(EnumType.STRING)
    @Column(
            name = "type",
            nullable = false,
            length = 50
    )
    private AlterationType type;

    @Column(
            name = "description",
            length = 2000
    )
    private String description;

    @Column(
            name = "price",
            nullable = false,
            precision = 10,
            scale = 2
    )
    @Builder.Default
    private BigDecimal price = BigDecimal.ZERO;

    public void setTailoringOrder(TailoringOrder tailoringOrder) {
        this.tailoringOrder = tailoringOrder;
    }
}