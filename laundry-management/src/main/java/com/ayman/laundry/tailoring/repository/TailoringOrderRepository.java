package com.ayman.laundry.tailoring.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ayman.laundry.tailoring.entity.TailoringOrder;
import com.ayman.laundry.tailoring.enums.TailoringStatus;

import java.util.List;
import java.util.Optional;


@Repository
public interface TailoringOrderRepository extends JpaRepository<TailoringOrder, Long> {


    // Find order by order number
    Optional<TailoringOrder> findByOrderNumber(String orderNumber);



    // Check duplicate order number
    boolean existsByOrderNumber(String orderNumber);



    // Get customer tailoring orders
    List<TailoringOrder> findByCustomerId(Long customerId);



    // Get orders assigned to tailor
    List<TailoringOrder> findByAssignedTailorId(Long employeeId);



    // Filter by status
    List<TailoringOrder> findByStatus(TailoringStatus status);


}