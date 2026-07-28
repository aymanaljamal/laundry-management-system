package com.ayman.laundry.common.entity;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;


@MappedSuperclass
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public abstract class BaseEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;



    // ===========================
    // Audit Information
    // ===========================

    @Column(
            nullable = false,
            updatable = false
    )
    private LocalDateTime createdAt;



    @Column(nullable = false)
    private LocalDateTime updatedAt;



    @Column(length = 100)
    private String createdBy;



    @Column(length = 100)
    private String updatedBy;



    // ===========================
    // Soft Delete
    // ===========================

    @Column(nullable = false)
    private Boolean deleted = false;



    @PrePersist
    protected void onCreate(){

        LocalDateTime now = LocalDateTime.now();

        this.createdAt = now;
        this.updatedAt = now;

        if(this.deleted == null){

            this.deleted = false;

        }

    }



    @PreUpdate
    protected void onUpdate(){

        this.updatedAt = LocalDateTime.now();

    }

}