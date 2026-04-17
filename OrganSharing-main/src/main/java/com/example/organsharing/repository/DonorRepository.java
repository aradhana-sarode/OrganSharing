package com.example.organsharing.repository;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.organsharing.service.*;

public interface DonorRepository extends JpaRepository<DonorService, Long> {
    DonorService findByBloodGroupAndOrganAndCity(String bloodGroup, String organ, String city);
    @Query("SELECT u FROM DonorService u WHERE u.bloodGroup = :bloodGroup")
    List<DonorService> findAllByBloodGroup(String bloodGroup);
}