package com.ayman.laundry.cloth.repository;

import com.ayman.laundry.cloth.entity.Cloth;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClothRepository extends JpaRepository<Cloth, Long> {


    Optional<Cloth> findByName(String name);


    boolean existsByName(String name);


}