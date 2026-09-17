package com.example.electricityalerts.service;

import com.example.electricityalerts.model.Household;
import com.example.electricityalerts.repository.HouseholdRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CreditMonitorService {

    private final HouseholdRepository householdRepository;
    private final NotificationService notificationService;

    public CreditMonitorService(HouseholdRepository householdRepository,
                                NotificationService notificationService) {
        this.householdRepository = householdRepository;
        this.notificationService = notificationService;
    }

    // Check balances every 30 seconds, offset 15 seconds from consumption
    @Scheduled(fixedRate = 30000, initialDelay = 15000)
    public void monitorCredits() {
        List<Household> households = householdRepository.findByNotifiedFalse();
        for (Household household : households) {
            double percentage = household.getRemainingPercentage();
            if (percentage <= household.getWarningThreshold()) {
                notificationService.sendAlert(household);
                household.setNotified(true);
                householdRepository.save(household);
                System.out.println("Alert triggered for: " + household.getHouseholdName()
                        + " at " + String.format("%.1f", percentage) + "%");
            }
        }
    }
}
