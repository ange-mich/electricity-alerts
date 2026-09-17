package com.example.electricityalerts.service;

import com.example.electricityalerts.model.Household;
import com.example.electricityalerts.repository.HouseholdRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class CreditConsumptionService {

    // Repository for reading and saving household records
    private final HouseholdRepository householdRepository;
    // Random number generator for simulating variable electricity usage
    private final Random random = new Random();

    public CreditConsumptionService(HouseholdRepository householdRepository) {
        this.householdRepository = householdRepository;
    }
    // Runs automatically every 30 seconds (30000 milliseconds)
    @Scheduled(fixedRate = 30000)
    public void consumeCredits() {
        List<Household> households = householdRepository.findAll();
        for (Household household : households) {
            // Only consume from households that still have credits
            if (household.getRemainingCredits() > 0) {
                // Deduct a random amount between 1 and 5
                int consumption = random.nextInt(5) + 1;
                int newBalance = Math.max(0, household.getRemainingCredits() - consumption);
                household.setRemainingCredits(newBalance);
                householdRepository.save(household);
            }
        }
    }
}