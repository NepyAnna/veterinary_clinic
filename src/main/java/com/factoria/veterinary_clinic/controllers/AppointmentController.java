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
        List<AppointmentDto> appointments = service.findAll().stream()
                .map(appointment -> new AppointmentDto(
                        appointment.getId(),
                        appointment.getPatient() != null ? appointment.getPatient().getId() : null,
                        appointment.getPatient() != null ? appointment.getPatient().getName() : null,
                        appointment.getAppointmentDateTime(),
                        appointment.getType(),
                        appointment.getReason(),
                        appointment.getStatus()))
                .toList();
        return ResponseEntity.ok(appointments);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AppointmentDto> show(@PathVariable Long id) {
        AppointmentDto appointment = service.findById(id);
        return ResponseEntity.ok(appointment);
    }

    @PostMapping("")
    public AppointmentDto store(@RequestBody AppointmentDto newAppointment) {
        return service.createAppointment(newAppointment);
    }

    @PutMapping("/{id}")
    public AppointmentDto updateAppointment(
            @PathVariable Long id,
            @RequestBody AppointmentDto updatedData) {
        return service.updateAppointment(id, updatedData);
    }

    @DeleteMapping("/{id}")
    public void deleteAppointment(@PathVariable Long id) {
        service.deleteAppointment(id);
    }
}
