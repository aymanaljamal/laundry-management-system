package com.ayman.laundry.tailoring.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ayman.laundry.tailoring.entity.Measurement;

import java.util.Optional;


@Repository
public interface MeasurementRepository extends JpaRepository<Measurement, Long> {


    // Get measurement by tailoring order
    Optional<Measurement> findByTailoringOrderId(Long tailoringOrderId);



    // Check if order has measurement
    boolean existsByTailoringOrderId(Long tailoringOrderId);


}