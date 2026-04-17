package com.example.organsharing.repository;



import org.springframework.data.jpa.repository.JpaRepository;

import com.example.organsharing.service.UserService;

public interface UserRepository extends JpaRepository<UserService, Long> {
    UserService findByEmail(String email);
}