package com.ayman.laundry.employee.entity;


import com.ayman.laundry.common.entity.BaseEntity;
import com.ayman.laundry.employee.enums.*;
import com.ayman.laundry.tailoring.entity.TailoringOrder;
import com.ayman.laundry.user.entity.User;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(
        name = "employees",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uk_employee_number",
                        columnNames = "employee_number"
                )
        }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Employee extends BaseEntity {


    @OneToOne(
            fetch = FetchType.LAZY,
            optional = false
    )
    @JoinColumn(
            name = "user_id",
            nullable = false,
            unique = true
    )
    private User user;



    @Column(
            name = "employee_number",
            nullable = false,
            length = 50
    )
    private String employeeNumber;



    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EmployeePosition position;



    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EmployeeDepartment department;



    @Column(nullable = false)
    private LocalDate hireDate;



    private LocalDate terminationDate;



    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EmployeeShift shift;



    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal salary;



    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @Builder.Default
    private EmployeeStatus status = EmployeeStatus.ACTIVE;

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<EmployeeTask> tasks = new ArrayList<>();

    @OneToMany(mappedBy = "assignedTailor", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<TailoringOrder> tailoringOrders = new ArrayList<>();

    public void terminateEmployee(){

        status = EmployeeStatus.TERMINATED;
        terminationDate = LocalDate.now();

    }


    public void activateEmployee(){

        status = EmployeeStatus.ACTIVE;
        terminationDate = null;

    }

}