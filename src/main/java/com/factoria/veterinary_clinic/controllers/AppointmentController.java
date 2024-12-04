package com.factoria.veterinary_clinic.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

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
    public List<AppointmentDto> index() {
        return service.findAll().stream()
                .map(appointment -> new AppointmentDto(
                        appointment.getPatient() != null ? appointment.getPatient().getId() : null,
                        appointment.getPatient() != null ? appointment.getPatient().getName() : null,
                        appointment.getAppointmentDateTime(),
                        appointment.getType(),
                        appointment.getReason(),
                        appointment.getStatus()))
                .toList();
    }

    @GetMapping("/{id}")
    public AppointmentDto getAppointmentById(@PathVariable Long id) {
        var appointment = service.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Appointment not found"));
        return new AppointmentDto(
                appointment.getPatient() != null ? appointment.getPatient().getId() : null,
                appointment.getPatient() != null ? appointment.getPatient().getName() : null,
                appointment.getAppointmentDateTime(),
                appointment.getType(),
                appointment.getReason(),
                appointment.getStatus());
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
