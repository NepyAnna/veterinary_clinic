package com.factoria.veterinary_clinic.services;

import com.factoria.veterinary_clinic.dtos.PatientDto;
import com.factoria.veterinary_clinic.models.Patient;
import com.factoria.veterinary_clinic.models.User;
import com.factoria.veterinary_clinic.repositories.PatientRepository;
import com.factoria.veterinary_clinic.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PatientService {

    private final PatientRepository patientRepository;
    private final UserRepository userRepository;

    @Autowired
    public PatientService(PatientRepository patientRepository, UserRepository userRepository) {
        this.patientRepository = patientRepository;
        this.userRepository = userRepository;
    }

    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    public Optional<Patient> getPatientById(Long id) {
        return patientRepository.findById(id);
    }

    public Patient createPatient(PatientDto patientDto) {
        Optional<User> user = userRepository.findById(patientDto.userId());

        if (user.isPresent()) {
            Patient patient = new Patient(
                    patientDto.name(),
                    patientDto.age(),
                    patientDto.breed(),
                    patientDto.gender(),
                    patientDto.identificationNumber(),
                    patientDto.guardianName(),
                    patientDto.guardianPhone(),
                    user.get());
            return patientRepository.save(patient);
        } else {
            throw new IllegalArgumentException("User not found with id: " + patientDto.userId());
        }
    }

    public Patient updatePatient(Long id, PatientDto patientDto) {
        Optional<Patient> existingPatient = patientRepository.findById(id);
        if (existingPatient.isPresent()) {
            Patient patient = existingPatient.get();
            patient.setName(patientDto.name());
            patient.setAge(patientDto.age());
            patient.setBreed(patientDto.breed());
            patient.setGender(patientDto.gender());
            patient.setIdentificationNumber(patientDto.identificationNumber());
            patient.setGuardianName(patientDto.guardianName());
            patient.setGuardianPhone(patientDto.guardianPhone());

            if (patientDto.userId() != null) {
                Optional<User> user = userRepository.findById(patientDto.userId());
                user.ifPresent(patient::setUser);
            }
            return patientRepository.save(patient);
        } else {
            throw new IllegalArgumentException("Patient not found with id: " + id);
        }
    }

    public void deletePatient(Long id) {
        Optional<Patient> patient = patientRepository.findById(id);
        if (patient.isPresent()) {
            patientRepository.delete(patient.get());
        } else {
            throw new IllegalArgumentException("Patient not found with id: " + id);
        }
    }
}
