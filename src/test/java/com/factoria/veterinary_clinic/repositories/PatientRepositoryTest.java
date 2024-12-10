package com.factoria.veterinary_clinic.repositories;

import com.factoria.veterinary_clinic.models.Patient;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class PatientRepositoryTest {
    @Autowired
    private PatientRepository patientRepository;

    @Test
    void testSavePatient() {
        Patient patient = new Patient();
        patient.setName("Bella");
        patient.setAge(5);
        patient.setBreed("Golden Retriever");

        Patient savedPatient = patientRepository.save(patient);

        assertThat(savedPatient.getId()).isNotNull();
        assertThat(savedPatient.getName()).isEqualTo("Bella");
        assertThat(savedPatient.getAge()).isEqualTo(5);
        assertThat(savedPatient.getBreed()).isEqualTo("Golden Retriever");
    }

    @Test
    void testFindById() {
        Patient patient = new Patient();
        patient.setName("Charlie");
        patient.setAge(3);
        patient = patientRepository.save(patient);

        Optional<Patient> foundPatient = patientRepository.findById(patient.getId());

        assertThat(foundPatient).isPresent();
        assertThat(foundPatient.get().getName()).isEqualTo("Charlie");
    }

    @Test
    void testFindAll() {
        Patient patient1 = new Patient();
        patient1.setName("Bella");
        patient1.setAge(5);

        Patient patient2 = new Patient();
        patient2.setName("Max");
        patient2.setAge(2);

        patientRepository.save(patient1);
        patientRepository.save(patient2);

        List<Patient> patients = patientRepository.findAll();

        assertThat(patients).hasSize(2);
        assertThat(patients).extracting(Patient::getName).containsExactlyInAnyOrder("Bella", "Max");
    }

    @Test
    void testDeletePatient() {
        Patient patient = new Patient();
        patient.setName("Bella");
        patient = patientRepository.save(patient);

        patientRepository.deleteById(patient.getId());

        Optional<Patient> deletedPatient = patientRepository.findById(patient.getId());
        assertThat(deletedPatient).isNotPresent();
    }
}
