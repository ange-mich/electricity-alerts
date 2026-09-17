package com.example.electricityalerts.service;

import com.example.electricityalerts.model.Household;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailNotificationService implements NotificationService {

    private final JavaMailSender mailSender;

    public EmailNotificationService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Override
    public void sendAlert(Household household) {
        // Build a simple email message
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(household.getOwnerEmail());
        message.setSubject("Low Electricity Credits Warning - " + household.getHouseholdName());
        message.setText(
                "Dear " + household.getHouseholdName() + " household,\n\n" +
                        "Your electricity credits are running low.\n\n" +
                        "Remaining credits: " + household.getRemainingCredits() + " out of " + household.getTotalCredits() + "\n" +
                        "Current level: " + String.format("%.1f", household.getRemainingPercentage()) + "%\n\n" +
                        "Please top up your credits as soon as possible to avoid a power cut.\n\n" +
                        "Electricity Credit Alert System"
        );
        // Send the email via Gmail SMTP
        mailSender.send(message);
        System.out.println("Email alert sent to: " + household.getOwnerEmail());
    }
}