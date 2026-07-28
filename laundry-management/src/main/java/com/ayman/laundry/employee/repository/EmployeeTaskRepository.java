package com.ayman.laundry.employee.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ayman.laundry.employee.entity.EmployeeTask;

import java.util.List;


@Repository
public interface EmployeeTaskRepository extends JpaRepository<EmployeeTask, Long> {


    List<EmployeeTask> findByEmployeeId(Long employeeId);


    List<EmployeeTask> findByOrderId(Long orderId);


}