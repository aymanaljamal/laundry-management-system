package com.ayman.laundry.tailoring.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ayman.laundry.tailoring.entity.Alteration;

import java.util.List;


@Repository
public interface AlterationRepository extends JpaRepository<Alteration, Long> {


    // Get all alterations for tailoring order
    List<Alteration> findByTailoringOrderId(Long tailoringOrderId);


}