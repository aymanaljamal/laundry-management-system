package com.ayman.laundry.tailoring.entity;

import com.ayman.laundry.common.entity.BaseEntity;
import com.ayman.laundry.customer.entity.Customer;
import com.ayman.laundry.employee.entity.Employee;
import com.ayman.laundry.payment.enums.PaymentStatus;
import com.ayman.laundry.tailoring.enums.TailoringPaymentMethod;
import com.ayman.laundry.tailoring.enums.TailoringPickupType;
import com.ayman.laundry.tailoring.enums.TailoringStatus;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(
        name = "tailoring_orders",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uk_tailoring_order_number",
                        columnNames = "order_number"
                )
        }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TailoringOrder extends BaseEntity {

    // ===========================
    // Customer
    // ===========================

    @ManyToOne(
            fetch = FetchType.LAZY,
            optional = false
    )
    @JoinColumn(
            name = "customer_id",
            nullable = false
    )
    private Customer customer;

    // ===========================
    // Assigned Tailor
    // ===========================

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "assigned_tailor_id")
    private Employee assignedTailor;

    // ===========================
    // Order Information
    // ===========================

    @Column(
            name = "order_number",
            nullable = false,
            length = 50
    )
    private String orderNumber;

    @Enumerated(EnumType.STRING)
    @Column(
            name = "status",
            nullable = false,
            length = 30
    )
    @Builder.Default
    private TailoringStatus status = TailoringStatus.PENDING;

    @Column(
            name = "description",
            length = 2000
    )
    private String description;

    // ===========================
    // Payment
    // ===========================

    @Column(
            name = "total_amount",
            nullable = false,
            precision = 10,
            scale = 2
    )
    @Builder.Default
    private BigDecimal totalAmount = BigDecimal.ZERO;

    @Column(
            name = "paid_amount",
            nullable = false,
            precision = 10,
            scale = 2
    )
    @Builder.Default
    private BigDecimal paidAmount = BigDecimal.ZERO;

    @Enumerated(EnumType.STRING)
    @Column(
            name = "payment_status",
            nullable = false,
            length = 30
    )
    @Builder.Default
    private PaymentStatus paymentStatus = PaymentStatus.PENDING;

    @Enumerated(EnumType.STRING)
    @Column(
            name = "payment_method",
            nullable = false,
            length = 30
    )
    @Builder.Default
    private TailoringPaymentMethod paymentMethod =
            TailoringPaymentMethod.CASH;

    // ===========================
    // Pickup / Delivery
    // ===========================

    @Enumerated(EnumType.STRING)
    @Column(
            name = "pickup_type",
            nullable = false,
            length = 30
    )
    @Builder.Default
    private TailoringPickupType pickupType =
            TailoringPickupType.IN_STORE;

    @Column(
            name = "delivery_address",
            length = 200
    )
    private String deliveryAddress;

    // ===========================
    // Dates
    // ===========================

    @Column(name = "pickup_date")
    private LocalDateTime pickupDate;

    @Column(name = "received_at")
    private LocalDateTime receivedAt;

    @Column(name = "completed_at")
    private LocalDateTime completedAt;

    // ===========================
    // Measurements
    // ===========================

    @OneToMany(
            mappedBy = "tailoringOrder",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @Builder.Default
    private List<Measurement> measurements =
            new ArrayList<>();

    // ===========================
    // Alterations
    // ===========================

    @OneToMany(
            mappedBy = "tailoringOrder",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @Builder.Default
    private List<Alteration> alterations =
            new ArrayList<>();

    // ===========================
    // Customer Helpers
    // ===========================

    public void assignCustomer(Customer customer) {
        this.customer = customer;
    }

    // ===========================
    // Tailor Helpers
    // ===========================

    public void assignTailor(Employee employee) {
        this.assignedTailor = employee;
    }

    public void removeAssignedTailor() {
        this.assignedTailor = null;
    }

    // ===========================
    // Measurement Helpers
    // ===========================

    public void addMeasurement(Measurement measurement) {

        if (measurement == null) {
            return;
        }

        if (!measurements.contains(measurement)) {
            measurements.add(measurement);
        }

        measurement.setTailoringOrder(this);
    }

    public void removeMeasurement(Measurement measurement) {

        if (measurement == null) {
            return;
        }

        if (measurements.remove(measurement)) {
            measurement.setTailoringOrder(null);
        }
    }

    // ===========================
    // Alteration Helpers
    // ===========================

    public void addAlteration(Alteration alteration) {

        if (alteration == null) {
            return;
        }

        if (!alterations.contains(alteration)) {
            alterations.add(alteration);
        }

        alteration.setTailoringOrder(this);
    }

    public void removeAlteration(Alteration alteration) {

        if (alteration == null) {
            return;
        }

        if (alterations.remove(alteration)) {
            alteration.setTailoringOrder(null);
        }
    }

    // ===========================
    // Payment Helpers
    // ===========================

    public void addPayment(BigDecimal amount) {

        if (amount == null
                || amount.compareTo(BigDecimal.ZERO) <= 0) {
            return;
        }

        BigDecimal currentPaidAmount =
                paidAmount != null
                        ? paidAmount
                        : BigDecimal.ZERO;

        BigDecimal currentTotalAmount =
                totalAmount != null
                        ? totalAmount
                        : BigDecimal.ZERO;

        BigDecimal newPaidAmount =
                currentPaidAmount.add(amount);

        if (newPaidAmount.compareTo(currentTotalAmount) > 0) {

            throw new IllegalArgumentException(
                    "Paid amount cannot exceed total amount"
            );
        }

        paidAmount = newPaidAmount;

        updatePaymentStatus();
    }

    public BigDecimal getRemainingAmount() {

        BigDecimal total =
                totalAmount != null
                        ? totalAmount
                        : BigDecimal.ZERO;

        BigDecimal paid =
                paidAmount != null
                        ? paidAmount
                        : BigDecimal.ZERO;

        return total
                .subtract(paid)
                .max(BigDecimal.ZERO);
    }

    public void updatePaymentStatus() {

        BigDecimal total =
                totalAmount != null
                        ? totalAmount
                        : BigDecimal.ZERO;

        BigDecimal paid =
                paidAmount != null
                        ? paidAmount
                        : BigDecimal.ZERO;

        if (paid.compareTo(BigDecimal.ZERO) <= 0) {

            paymentStatus = PaymentStatus.PENDING;

            return;
        }

        if (total.compareTo(BigDecimal.ZERO) > 0
                && paid.compareTo(total) >= 0) {

            paymentStatus = PaymentStatus.COMPLETED;

            return;
        }

        // لا يوجد PARTIALLY_PAID داخل PaymentStatus حاليًا
        paymentStatus = PaymentStatus.PENDING;
    }

    // ===========================
    // Status Helpers
    // ===========================

    public void markAsReceived() {
        this.receivedAt = LocalDateTime.now();
    }

    public void markAsCompleted() {
        this.status = TailoringStatus.COMPLETED;
        this.completedAt = LocalDateTime.now();
    }
}