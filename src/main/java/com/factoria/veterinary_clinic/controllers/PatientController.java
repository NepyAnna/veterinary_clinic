package com.factoria.veterinary_clinic.controllers;

import com.factoria.veterinary_clinic.dtos.AppointmentDto;
import com.factoria.veterinary_clinic.dtos.PatientDto;
import com.factoria.veterinary_clinic.models.Patient;
import com.factoria.veterinary_clinic.services.PatientService;
import com.factoria.veterinary_clinic.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/patients")
public class PatientController {

    private final PatientService patientService;
    private final UserService userService;

    public PatientController(PatientService patientService, UserService userService) {
        this.patientService = patientService;
        this.userService = userService;
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    @GetMapping
    public ResponseEntity<List<PatientDto>> getAllPatients(@AuthenticationPrincipal UserDetails userDetails) {
        List<Patient> patients;

        if (userDetails.getAuthorities().toString().contains("ROLE_ADMIN")) {
            patients = patientService.getAllPatients();
        } else if (userDetails.getAuthorities().toString().contains("ROLE_USER")) {
            Long userId = getCurrentUserId(userDetails);
            patients = patientService.getPatientsByUserId(userId);
        } else {
            return ResponseEntity.status(403).body(null);
        }

        List<PatientDto> patientDtos = patients.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());

        return ResponseEntity.ok(patientDtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PatientDto> getPatientById(@PathVariable Long id,
            @AuthenticationPrincipal UserDetails userDetails) {
        Optional<Patient> patient = patientService.getPatientById(id);

        if (patient.isPresent()) {
            if (isCurrentUserPatient(id, userDetails)
                    || userDetails.getAuthorities().toString().contains("ROLE_ADMIN")) {
                return ResponseEntity.ok(convertToDto(patient.get()));
            } else {
                return ResponseEntity.status(403).body(null);
            }
        } else {
            return ResponseEntity.status(404).body(null);
        }
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    @PostMapping
    public ResponseEntity<Patient> createPatient(@RequestBody PatientDto patientDto,
            @AuthenticationPrincipal UserDetails userDetails) {
        if (userDetails.getAuthorities().toString().contains("ROLE_USER")) {
            Long userId = getCurrentUserId(userDetails);
            patientDto = new PatientDto(patientDto.id(), patientDto.name(), patientDto.age(), patientDto.breed(),
                    patientDto.gender(), patientDto.identificationNumber(), patientDto.guardianName(),
                    patientDto.guardianPhone(), userId, patientDto.appointments());
        }

        Patient patient = patientService.createPatient(patientDto);
        return ResponseEntity.ok(patient);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PatientDto> updatePatient(@PathVariable Long id, @RequestBody PatientDto patientDto,
            @AuthenticationPrincipal UserDetails userDetails) {
        try {

            if (userDetails.getAuthorities().toString().contains("ROLE_USER")) {
                Long userId = getCurrentUserId(userDetails);

                Optional<Patient> existingPatient = patientService.getPatientById(id);
                if (existingPatient.isPresent()) {
                    Patient patient = existingPatient.get();
                    if (!patient.getUser().getId().equals(userId)) {
                        return ResponseEntity.status(403).body(null);
                    }
                }
            }

            Patient updatedPatient = patientService.updatePatient(id, patientDto);
            PatientDto updatedPatientDto = convertToDto(updatedPatient);

            return ResponseEntity.ok(updatedPatientDto);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PreAuthorize("hasRole('ADMIN') or @patientController.isCurrentUserPatient(#id, authentication)")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePatient(@PathVariable Long id, @AuthenticationPrincipal UserDetails userDetails) {
        try {

            if (!isCurrentUserPatient(id, userDetails)
                    && !userDetails.getAuthorities().toString().contains("ROLE_ADMIN")) {
                return ResponseEntity.status(403).body(null);
            }

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

    public boolean isCurrentUserPatient(Long patientId, UserDetails userDetails) {
        Optional<Patient> patient = patientService.getPatientById(patientId);

        if (patient.isPresent()) {
            return patient.get().getUser().getEmail().equals(userDetails.getUsername());
        } else {
            return false;
        }
    }

    public boolean isCurrentUserPatientCreation(UserDetails userDetails) {
        return true;
    }

    private Long getCurrentUserId(UserDetails userDetails) {
        return userService.getUserByEmail(userDetails.getUsername()).get().getId();
    }
}
