package com.example.electricityalerts.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

// Maps this class to a database table
@Entity
public class Household {
    // Auto-generated primary key
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String householdName;
    @NotBlank
    @Email
    private String ownerEmail;
    @NotBlank
    private String phoneNumber;
    @Positive
    private int totalCredits;
    private int remainingCredits;
    // Percentage threshold for low-credit warning
    private int warningThreshold;
    // Tracks whether an alert has already been sent
    private boolean notified;

    // Default constructor (Required by JPA)
    public Household() {
        // Keep this empty for JPA entity mapping
    }

    // Parameterized constructor
    public Household(String householdName, String ownerEmail, String phoneNumber, int totalCredits) {
        this.householdName = householdName;
        this.ownerEmail = ownerEmail;
        this.phoneNumber = phoneNumber;
        this.totalCredits = totalCredits;
        this.remainingCredits = totalCredits;
        this.warningThreshold = 20;
        this.notified = false;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getHouseholdName() { return householdName; }
    public void setHouseholdName(String householdName) { this.householdName = householdName; }

    public String getOwnerEmail() { return ownerEmail; }
    public void setOwnerEmail(String ownerEmail) { this.ownerEmail = ownerEmail; }

    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }

    public int getTotalCredits() { return totalCredits; }
    public void setTotalCredits(int totalCredits) { this.totalCredits = totalCredits; }

    public int getRemainingCredits() { return remainingCredits; }
    public void setRemainingCredits(int remainingCredits) { this.remainingCredits = remainingCredits; }

    public int getWarningThreshold() { return warningThreshold; }
    public void setWarningThreshold(int warningThreshold) { this.warningThreshold = warningThreshold; }

    public boolean isNotified() { return notified; }
    public void setNotified(boolean notified) { this.notified = notified; }

    // Calculates how much credit remains as a percentage
    public double getRemainingPercentage() {
        if (totalCredits == 0) return 0;
        return (double) remainingCredits / totalCredits * 100;
    }
}