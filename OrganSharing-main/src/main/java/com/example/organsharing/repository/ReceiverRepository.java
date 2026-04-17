package com.example.organsharing.repository;



import org.springframework.data.jpa.repository.JpaRepository;

import com.example.organsharing.service.Receiver;

public interface ReceiverRepository extends JpaRepository<Receiver, Long> {
}