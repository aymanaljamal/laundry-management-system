package com.ayman.laundry.employee.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ayman.laundry.employee.entity.Employee;

import java.util.Optional;


@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {


    Optional<Employee> findByEmployeeNumber(String employeeNumber);


    Optional<Employee> findByUserId(Long userId);


    boolean existsByEmployeeNumber(String employeeNumber);


}