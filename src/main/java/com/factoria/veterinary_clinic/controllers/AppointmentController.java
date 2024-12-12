package com.factoria.veterinary_clinic.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import com.factoria.veterinary_clinic.dtos.AppointmentDto;
import com.factoria.veterinary_clinic.services.AppointmentService;
import com.factoria.veterinary_clinic.services.PatientService;

import java.util.List;

@RestController
@RequestMapping("/api/appointments")
public class AppointmentController {

    private final AppointmentService appointmentService;
    private final PatientService patientService;

    public AppointmentController(AppointmentService appointmentService, PatientService patientService) {
        this.appointmentService = appointmentService;
        this.patientService = patientService;
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    @GetMapping
    public ResponseEntity<List<AppointmentDto>> index() {
        List<AppointmentDto> appointments = appointmentService.findAll();
        return ResponseEntity.ok(appointments);
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    @GetMapping("/{id}")
    public ResponseEntity<AppointmentDto> show(@PathVariable Long id) {
        AppointmentDto appointment = appointmentService.findById(id);
        return ResponseEntity.ok(appointment);
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    @PostMapping
    public ResponseEntity<AppointmentDto> store(
            @RequestBody AppointmentDto newAppointment,
            @AuthenticationPrincipal UserDetails userDetails) {
        if (userDetails.getAuthorities().toString().contains("ROLE_USER")) {

            boolean isPatientOwner = patientService.isPatientOwnedByUser(newAppointment.getPatientId(),
                    userDetails.getUsername());
            if (!isPatientOwner) {
                return ResponseEntity.status(403).build();
            }
        }
        AppointmentDto createdAppointment = appointmentService.createAppointment(newAppointment);
        return ResponseEntity.ok(createdAppointment);
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    @PutMapping("/{id}")
    public ResponseEntity<AppointmentDto> updateAppointment(
            @PathVariable Long id,
            @RequestBody AppointmentDto updatedData) {
        AppointmentDto updatedAppointment = appointmentService.updateAppointment(id, updatedData);
        return ResponseEntity.ok(updatedAppointment);
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAppointment(
            @PathVariable Long id,
            @AuthenticationPrincipal UserDetails userDetails) {
        if (userDetails.getAuthorities().toString().contains("ROLE_USER")) {
            AppointmentDto appointment = appointmentService.findById(id);
            boolean isPatientOwner = patientService.isPatientOwnedByUser(appointment.getPatientId(),
                    userDetails.getUsername());
            if (!isPatientOwner) {
                return ResponseEntity.status(403).build();
            }
        }
        appointmentService.deleteAppointment(id);
        return ResponseEntity.noContent().build();
    }
}
