package com.ayman.laundry.notification.entity;


import com.ayman.laundry.common.entity.BaseEntity;
import com.ayman.laundry.notification.enums.*;
import com.ayman.laundry.order.entity.Order;
import com.ayman.laundry.tailoring.entity.TailoringOrder;
import com.ayman.laundry.user.entity.User;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "notifications")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Notification extends BaseEntity {



    // ===========================
    // User Relation
    // ===========================

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "user_id",
            nullable = false
    )
    private User user;



    // ===========================
    // Order Relation
    // ===========================

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "order_id"
    )
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "tailoring_order_id"
    )
    private TailoringOrder tailoringOrder;

    // ===========================
    // Notification Information
    // ===========================

    @Column(nullable = false, length = 200)
    private String title;



    @Column(nullable = false, length = 500)
    private String message;



    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private NotificationType type;



    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @Builder.Default
    private NotificationStatus status = NotificationStatus.UNREAD;



    // ===========================
    // Helper Method
    // ===========================

    public void markAsRead(){

        this.status = NotificationStatus.READ;

    }


}