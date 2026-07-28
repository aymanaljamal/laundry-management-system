package com.ayman.laundry.service.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ayman.laundry.service.entity.LaundryService;

import java.util.List;
import java.util.Optional;


@Repository
public interface LaundryServiceRepository extends JpaRepository<LaundryService, Long> {


    // Find service by name
    Optional<LaundryService> findByName(String name);



    // Check duplicate service name
    boolean existsByName(String name);



    // Get active services
    List<LaundryService> findByActiveTrue();


}