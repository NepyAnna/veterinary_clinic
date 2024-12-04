package com.factoria.veterinary_clinic.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.factoria.veterinary_clinic.dtos.PatientDto;
import com.factoria.veterinary_clinic.models.Patient;
import com.factoria.veterinary_clinic.models.User;
import com.factoria.veterinary_clinic.repositories.PatientRepository;
import com.factoria.veterinary_clinic.repositories.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class PatientService {
    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    @Transactional
    public Optional<Patient> getPatientById(Long id) {
        return patientRepository.findById(id);
    }

    @Transactional
    public Patient addPatient(PatientDto patientDto) {
        User user = userRepository.findById(patientDto.userId())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        Patient patient = new Patient(
                patientDto.name(),
                patientDto.age(),
                patientDto.breed(),
                patientDto.gender(),
                patientDto.identificationNumber(),
                patientDto.guardianName(),
                patientDto.guardianPhone(),
                user);
        return patientRepository.save(patient);
    }

    @Transactional
    public Patient updatePatient(Long id, PatientDto patientDto) {
        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Patient not found"));

        User user = userRepository.findById(patientDto.userId())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        patient.setName(patientDto.name());
        patient.setAge(patientDto.age());
        patient.setBreed(patientDto.breed());
        patient.setGender(patientDto.gender());
        patient.setIdentificationNumber(patientDto.identificationNumber());
        patient.setGuardianName(patientDto.guardianName());
        patient.setGuardianPhone(patientDto.guardianPhone());
        patient.setUser(user);

        patientRepository.save(patient);
        return patient;
    }

    @Transactional
    public void deletePatient(Long id) {
        if (!patientRepository.existsById(id)) {
            throw new IllegalArgumentException("Patient not found");
        }
        patientRepository.deleteById(id);
    }
}
