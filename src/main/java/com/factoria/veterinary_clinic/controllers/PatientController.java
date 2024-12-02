package com.factoria.veterinary_clinic.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.factoria.veterinary_clinic.dtos.PatientDto;
import com.factoria.veterinary_clinic.models.Patient;
import com.factoria.veterinary_clinic.services.PatientService;


@RestController
@RequestMapping("/api/patients")

public class PatientController {

    @Autowired
    private PatientService patientService;

    @GetMapping("/all")
    public ResponseEntity<List<Patient>>getAllPatients(){
        List<Patient>patients=patientService.getAllPatients();
        return ResponseEntity.ok(patients);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Patient> getPatientById(@PathVariable Long id) {
        Optional<Patient> patient = patientService.getPatientById(id);
        return patient.map(ResponseEntity::ok)
        .orElseGet(()->ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
    
}
    @PostMapping("/add")
     public ResponseEntity<String> createPatient(@RequestBody PatientDto patientDto) {
        try {
            Patient createdPatient = patientService.addPatient(patientDto);
            return ResponseEntity.status(HttpStatus.CREATED).body("Patient created with ID: " + createdPatient.getId());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid data: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred: " + e.getMessage());
        }
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<String> updatePatient(@PathVariable Long id, @RequestBody PatientDto patientDto) {
        try {
            patientService.updatePatient(id, patientDto);
            return ResponseEntity.ok("Patient updated successfully");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Patient or type not found: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while updating the patient: " + e.getMessage());
        }
    }
     @DeleteMapping("/delete/{id}")
     public ResponseEntity<String> deletePatient(@PathVariable Long id) {
        try {
            patientService.deletePatient(id);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Patient not found: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while deleting the patient: " + e.getMessage());
        }
    }
}