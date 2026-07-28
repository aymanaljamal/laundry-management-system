package com.ayman.laundry.tailoring.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.ayman.laundry.common.entity.BaseEntity;
import com.ayman.laundry.customer.entity.Customer;
import com.ayman.laundry.employee.entity.Employee;
import com.ayman.laundry.payment.enums.PaymentStatus;
import com.ayman.laundry.tailoring.enums.TailoringPaymentMethod;
import com.ayman.laundry.tailoring.enums.TailoringPickupType;
import com.ayman.laundry.tailoring.enums.TailoringStatus;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tailoring_orders")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TailoringOrder extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "assigned_tailor_id")
    private Employee assignedTailor;

    @Column(name = "order_number", nullable = false, length = 50)
    private String orderNumber;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @Builder.Default
    private TailoringStatus status = TailoringStatus.PENDING;

    @Column(length = 2000)
    private String description;

    @Column(nullable = false, precision = 10, scale = 2)
    @Builder.Default
    private BigDecimal totalAmount = BigDecimal.ZERO;

    @Column(precision = 10, scale = 2)
    @Builder.Default
    private BigDecimal paidAmount = BigDecimal.ZERO;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @Builder.Default
    private PaymentStatus paymentStatus = PaymentStatus.PENDING;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @Builder.Default
    private TailoringPaymentMethod paymentMethod = TailoringPaymentMethod.CASH;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @Builder.Default
    private TailoringPickupType pickupType = TailoringPickupType.IN_STORE;

    @Column(length = 200)
    private String deliveryAddress;

    private LocalDateTime pickupDate;

    private LocalDateTime receivedAt;

    private LocalDateTime completedAt;

    @OneToMany(mappedBy = "tailoringOrder", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<Measurement> measurements = new ArrayList<>();

    @OneToMany(mappedBy = "tailoringOrder", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<Alteration> alterations = new ArrayList<>();
}
