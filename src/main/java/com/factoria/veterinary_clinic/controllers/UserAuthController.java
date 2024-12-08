package com.factoria.veterinary_clinic.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserAuthController {

    @GetMapping("/profile")
    public String getUserProfile() {
        return "User Profile Information";
    }

    @GetMapping("/appointments")
    public String getUserAppointments() {
        return "List of appointments for the user";
    }
}
