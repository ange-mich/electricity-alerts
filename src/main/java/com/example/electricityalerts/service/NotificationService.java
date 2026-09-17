package com.example.electricityalerts.service;

import com.example.electricityalerts.model.Household;

public interface NotificationService {
    void sendAlert(Household household);
}