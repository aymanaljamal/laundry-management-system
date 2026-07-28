package com.ayman.laundry.coupon.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.ayman.laundry.common.entity.BaseEntity;
import com.ayman.laundry.coupon.enums.CouponType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(
        name = "coupons",
        uniqueConstraints = {
                @UniqueConstraint(name = "uk_coupon_code", columnNames = "code")
        }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Coupon extends BaseEntity {

    @Column(nullable = false, length = 50)
    private String code;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CouponType type;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal value;

    @Column(nullable = false)
    private LocalDateTime expiryDate;

    @Column(nullable = false)
    @Builder.Default
    private Boolean active = true;
}
