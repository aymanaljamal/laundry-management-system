package com.ayman.laundry.customer.repository;

import com.ayman.laundry.customer.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {


    Optional<Customer> findByCustomerNumber(String customerNumber);


    boolean existsByCustomerNumber(String customerNumber);


    Optional<Customer> findByPhoneNumber(String phoneNumber);


    Optional<Customer> findByUserId(Long userId);


}