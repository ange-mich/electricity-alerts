package com.example.electricityalerts.controller;

import com.example.electricityalerts.model.Household;
import com.example.electricityalerts.repository.HouseholdRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class AdminController {

    private final HouseholdRepository householdRepository;

    // Spring injects the repository automatically
    public AdminController(HouseholdRepository householdRepository) {
        this.householdRepository = householdRepository;
    }

    // Loads all households and passes them to the template
    @GetMapping("/admin")
    public String adminDashboard(Model model) {
        model.addAttribute("households", householdRepository.findAll());
        return "admin";
    }

    // Handles the add-household form submission
    @PostMapping("/admin/add")
    public String addHousehold(@Valid @ModelAttribute Household household,
                               RedirectAttributes redirectAttributes) {
        Household newHousehold = new Household(
            household.getHouseholdName(),
            household.getOwnerEmail(),
            household.getPhoneNumber(),
            household.getTotalCredits());
        householdRepository.save(newHousehold);

        // Flash message shown after redirect
        redirectAttributes.addFlashAttribute("success", "Household added successfully!");
        return "redirect:/admin";
    }
} // <-- The class is now closed at the very end of the file