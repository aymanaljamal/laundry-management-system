package com.ayman.laundry.notification.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ayman.laundry.notification.entity.Notification;
import com.ayman.laundry.notification.enums.NotificationStatus;

import java.util.List;


@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {


    // Get all notifications for user
    List<Notification> findByUserId(Long userId);



    // Get notifications by status (READ / UNREAD)
    List<Notification> findByUserIdAndStatus(
            Long userId,
            NotificationStatus status
    );



    // Get order related notifications
    List<Notification> findByOrderId(Long orderId);



    // Get tailoring order related notifications
    List<Notification> findByTailoringOrderId(Long tailoringOrderId);


}