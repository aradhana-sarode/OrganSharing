package com.example.organsharing.repository;




import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.organsharing.service.DonorService;

public interface DonorRepository extends JpaRepository<DonorService, Long> {

    List<DonorService> findByBloodGroup(String bloodGroup);

    List<DonorService> findByBloodGroupAndOrgan(String bloodGroup, String organ);
}