package com.factoria.veterinary_clinic.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.factoria.veterinary_clinic.dtos.AppointmentDto;
import com.factoria.veterinary_clinic.services.AppointmentService;
import java.util.List;

@RestController
@RequestMapping("/api/appointments")
public class AppointmentController {

    private final AppointmentService service;

    public AppointmentController(AppointmentService service) {
        this.service = service;
    }

    @GetMapping("")
    public ResponseEntity<List<AppointmentDto>> index() {
        List<AppointmentDto> appointments = service.findAll();
        return ResponseEntity.ok(appointments);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AppointmentDto> show(@PathVariable Long id) {
        AppointmentDto appointment = service.findById(id);
        return ResponseEntity.ok(appointment);
    }

    @PostMapping("")
    public ResponseEntity<AppointmentDto> store(@RequestBody AppointmentDto newAppointment) {
        AppointmentDto createdAppointment = service.createAppointment(newAppointment);
        return ResponseEntity.ok(createdAppointment);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AppointmentDto> updateAppointment(@PathVariable Long id, @RequestBody AppointmentDto updatedData) {
        AppointmentDto updatedAppointment = service.updateAppointment(id, updatedData);
        return ResponseEntity.ok(updatedAppointment);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAppointment(@PathVariable Long id) {
        service.deleteAppointment(id);
        return ResponseEntity.noContent().build();
    }
}
