package com.ayman.laundry.delivery.repository;

import com.ayman.laundry.delivery.entity.Delivery;
import com.ayman.laundry.delivery.enums.DeliveryStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DeliveryRepository extends JpaRepository<Delivery, Long> {


    Optional<Delivery> findByOrderId(Long orderId);


    boolean existsByOrderId(Long orderId);


    List<Delivery> findByStatus(DeliveryStatus status);


    List<Delivery> findByDeliveryEmployeeId(Long employeeId);


}