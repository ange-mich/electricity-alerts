package com.example.electricityalerts.repository;

import com.example.electricityalerts.model.Household;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface HouseholdRepository extends JpaRepository<Household, Long> {
    // Finds all households that have not been notified yet
    List<Household> findByNotifiedFalse();
}