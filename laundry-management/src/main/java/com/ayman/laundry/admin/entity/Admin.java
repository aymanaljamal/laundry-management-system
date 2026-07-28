package com.ayman.laundry.admin.entity;


import com.ayman.laundry.admin.enums.*;
import com.ayman.laundry.common.entity.BaseEntity;
import com.ayman.laundry.user.entity.User;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(
        name = "admins",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uk_admin_employee_number",
                        columnNames = "employee_number"
                )
        }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Admin extends BaseEntity {


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
    private AdminDepartment department;



    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AdminPosition position;



    @Column(nullable = false)
    @Builder.Default
    private Boolean superAdmin = false;



    public void promoteToSuperAdmin(){

        superAdmin = true;

    }


    public void removeSuperAdmin(){

        superAdmin = false;

    }

}