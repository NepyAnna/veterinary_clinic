package com.factoria.veterinary_clinic.services;

//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.request;

import java.util.List;
import java.util.Optional;

//import javax.crypto.spec.PBEParameterSpec;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.factoria.veterinary_clinic.dtos.PatientDto;
import com.factoria.veterinary_clinic.models.Patient;
import com.factoria.veterinary_clinic.repositories.PatientRepository;
import com.factoria.veterinary_clinic.repositories.UserRepository;

//import aj.org.objectweb.asm.Type;
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
       /* Patient type = userRepository.findByName(request.getTypeName())*/
          /*  .orElseThrow(() -> new IllegalArgumentException("Type not found"));*/

        Patient patient = new Patient(
        patientDto.name(),
        patientDto.age(), 
        patientDto.breed(),
        patientDto.gender(), 
        patientDto.identificationNumber(), 
        patientDto.GuardianName(),
        patientDto.guardianPhone(), 
        null);
        return patientRepository.save(patient);
    }
     @Transactional
    public Patient updatePatient(Long id, PatientDto patientDto) {
       /* Type type = userRepository.findById()
            .orElseThrow(() -> new IllegalArgumentException("Type not found"));*/

        Patient patient = patientRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Patient not found"));

        patient.setName(patientDto.name());
        patient.setAge(patientDto.age());
        patient.setBreed(patientDto.breed());
        patient.setGender(patientDto.gender());
        patient.setIdentificationNumber(patientDto.identificationNumber());
        patient.setGuardianName(patientDto.GuardianName());
        patient.setGuardianPhone(patientDto.guardianPhone());

        patientRepository.save(patient);
        return null;
    }
    @Transactional
    public void deletePatient(Long id) {
        if (!patientRepository.existsById(id)) {
            throw new IllegalArgumentException("Patient not found");
        }
        patientRepository.deleteById(id);
    }
}

 
