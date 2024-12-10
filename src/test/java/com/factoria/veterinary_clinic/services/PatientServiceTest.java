package com.factoria.veterinary_clinic.services;

import com.factoria.veterinary_clinic.dtos.PatientDto;
import com.factoria.veterinary_clinic.models.Patient;
import com.factoria.veterinary_clinic.models.User;
import com.factoria.veterinary_clinic.repositories.PatientRepository;
import com.factoria.veterinary_clinic.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class PatientServiceTest {
    @Mock
    private PatientRepository patientRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private PatientService patientService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    void testCreatePatient() {
        User user = new User();
        user.setId(1L);

        PatientDto patientDto = new PatientDto(
                null,
                "Bella",
                5,
                "Golden Retriever",
                "Female",
                "ID123",
                "John Doe",
                "1234567890",
                1L,
                List.of()
        );

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        Patient patient = new Patient(
                "Bella",
                5,
                "Golden Retriever",
                "Female",
                "ID123",
                "John Doe",
                "1234567890",
                user
        );

        when(patientRepository.save(any(Patient.class))).thenReturn(patient);

        Patient createdPatient = patientService.createPatient(patientDto);

        assertThat(createdPatient.getName()).isEqualTo("Bella");
        assertThat(createdPatient.getUser()).isEqualTo(user);
        verify(patientRepository, times(1)).save(any(Patient.class));
    }

    @Test
    void testDeletePatient() {
        Patient patient = new Patient();
        patient.setId_patient(1L);

        when(patientRepository.findById(1L)).thenReturn(Optional.of(patient));

        // Act
        patientService.deletePatient(1L);

        // Assert
        verify(patientRepository, times(1)).delete(patient);
    }

    @Test
    void testGetAllPatients() {
        Patient patient1 = new Patient();
        patient1.setName("Bella");

        Patient patient2 = new Patient();
        patient2.setName("Max");

        when(patientRepository.findAll()).thenReturn(List.of(patient1, patient2));

        List<Patient> patients = patientService.getAllPatients();

        assertThat(patients).hasSize(2);
        assertThat(patients).extracting(Patient::getName).containsExactlyInAnyOrder("Bella", "Max");
    }

    @Test
    void testGetPatientById() {
        Patient patient = new Patient();
        patient.setId_patient(1L);
        patient.setName("Bella");

        when(patientRepository.findById(1L)).thenReturn(Optional.of(patient));

        Optional<Patient> result = patientService.getPatientById(1L);

        assertThat(result).isPresent();
        assertThat(result.get().getName()).isEqualTo("Bella");
    }

    @Test
    void testUpdatePatient() {
        Patient existingPatient = new Patient();
        existingPatient.setId_patient(1L);
        existingPatient.setName("Bella");

        PatientDto patientDto = new PatientDto(
                1L,
                "Max",
                3,
                "Labrador",
                "Male",
                "ID456",
                "Jane Doe",
                "0987654321",
                null,
                List.of()
        );

        when(patientRepository.findById(1L)).thenReturn(Optional.of(existingPatient));
        when(patientRepository.save(any(Patient.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Patient updatedPatient = patientService.updatePatient(1L, patientDto);

        assertThat(updatedPatient.getName()).isEqualTo("Max");
        assertThat(updatedPatient.getAge()).isEqualTo(3);
        verify(patientRepository, times(1)).save(any(Patient.class));
    }
}
