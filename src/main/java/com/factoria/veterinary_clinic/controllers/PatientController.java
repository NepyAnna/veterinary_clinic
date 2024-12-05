package com.factoria.veterinary_clinic.controllers;

import com.factoria.veterinary_clinic.dtos.AppointmentDto;
import com.factoria.veterinary_clinic.dtos.PatientDto;
import com.factoria.veterinary_clinic.models.Patient;
import com.factoria.veterinary_clinic.services.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/patients")
public class PatientController {

    private final PatientService patientService;

    @Autowired
    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping
    public List<PatientDto> getAllPatients() {
        List<Patient> patients = patientService.getAllPatients();
        return patients.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PatientDto> getPatientById(@PathVariable Long id) {
        Optional<Patient> patient = patientService.getPatientById(id);
        return patient.map(p -> ResponseEntity.ok(convertToDto(p)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Patient> createPatient(@RequestBody PatientDto patientDto) {
        Patient patient = patientService.createPatient(patientDto);
        return ResponseEntity.ok(patient);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PatientDto> updatePatient(@PathVariable Long id, @RequestBody PatientDto patientDto) {
        try {
            Patient updatedPatient = patientService.updatePatient(id, patientDto);

            PatientDto updatedPatientDto = convertToDto(updatedPatient);

            return ResponseEntity.ok(updatedPatientDto);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePatient(@PathVariable Long id) {
        try {
            patientService.deletePatient(id);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    private PatientDto convertToDto(Patient patient) {
        List<AppointmentDto> appointmentDtos = patient.getAppointments().stream()
                .map(appointment -> new AppointmentDto(
                        appointment.getId(),
                        patient.getId(),
                        patient.getName(),
                        appointment.getAppointmentDateTime(),
                        appointment.getType(),
                        appointment.getReason(),
                        appointment.getStatus()))
                .collect(Collectors.toList());

        Long userId = (patient.getUser() != null) ? patient.getUser().getId() : null;

        return new PatientDto(
                patient.getId(),
                patient.getName(),
                patient.getAge(),
                patient.getBreed(),
                patient.getGender(),
                patient.getIdentificationNumber(),
                patient.getGuardianName(),
                patient.getGuardianPhone(),
                userId,
                appointmentDtos);
    }
}
