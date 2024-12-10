package com.factoria.veterinary_clinic.dtos;

import org.junit.jupiter.api.Test;
import java.util.Collections;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class PatientDtoTest {
    @Test
    void testAge() {
        PatientDto patient = new PatientDto(1L, "Bella", 3, "Golden Retriever", "Female",
        "12345", "John Doe", "555-1234", 1L, Collections.emptyList());
assertEquals(3, patient.age());
    }

    @Test
    void testAppointments() {
        AppointmentDto appointment = new AppointmentDto(1, 1, "Bella", "2023-12-10T10:00",
        "Checkup", "Routine Check", "Scheduled");
PatientDto patient = new PatientDto(1L, "Bella", 3, "Golden Retriever", "Female",
        "12345", "John Doe", "555-1234", 1L, List.of(appointment));
assertEquals(1, patient.appointments().size());
assertEquals(appointment, patient.appointments().get(0));
    }

    @Test
    void testBreed() {
            PatientDto patient = new PatientDto(1L, "Bella", 3, "Golden Retriever", "Female",
                    "12345", "John Doe", "555-1234", 1L, Collections.emptyList());
            assertEquals("Golden Retriever", patient.breed());
    }

    @Test
    void testEquals() {
        PatientDto patient1 = new PatientDto(1L, "Bella", 3, "Golden Retriever", "Female",
        "12345", "John Doe", "555-1234", 1L, Collections.emptyList());
PatientDto patient2 = new PatientDto(1L, "Bella", 3, "Golden Retriever", "Female",
        "12345", "John Doe", "555-1234", 1L, Collections.emptyList());
assertEquals(patient1, patient2);
    }

    @Test
    void testGender() {
        PatientDto patient = new PatientDto(1L, "Bella", 3, "Golden Retriever", "Female",
        "12345", "John Doe", "555-1234", 1L, Collections.emptyList());
assertEquals("Female", patient.gender());
    }

    @Test
    void testGuardianName() {
        PatientDto patient = new PatientDto(1L, "Bella", 3, "Golden Retriever", "Female",
        "12345", "John Doe", "555-1234", 1L, Collections.emptyList());
assertEquals("John Doe", patient.guardianName());
    }

    @Test
    void testGuardianPhone() {
        PatientDto patient = new PatientDto(1L, "Bella", 3, "Golden Retriever", "Female",
        "12345", "John Doe", "555-1234", 1L, Collections.emptyList());
assertEquals("555-1234", patient.guardianPhone());
    }

    @Test
    void testHashCode() {
        PatientDto patient1 = new PatientDto(1L, "Bella", 3, "Golden Retriever", "Female",
        "12345", "John Doe", "555-1234", 1L, Collections.emptyList());
PatientDto patient2 = new PatientDto(1L, "Bella", 3, "Golden Retriever", "Female",
        "12345", "John Doe", "555-1234", 1L, Collections.emptyList());
assertEquals(patient1.hashCode(), patient2.hashCode());
    }

    @Test
    void testId() {
        PatientDto patient = new PatientDto(1L, "Bella", 3, "Golden Retriever", "Female",
        "12345", "John Doe", "555-1234", 1L, Collections.emptyList());
assertEquals(1L, patient.id());
    }

    @Test
    void testIdentificationNumber() {
        PatientDto patient = new PatientDto(1L, "Bella", 3, "Golden Retriever", "Female",
        "12345", "John Doe", "555-1234", 1L, Collections.emptyList());
assertEquals("12345", patient.identificationNumber());
    }

    @Test
    void testName() {
        PatientDto patient = new PatientDto(1L, "Bella", 3, "Golden Retriever", "Female",
        "12345", "John Doe", "555-1234", 1L, Collections.emptyList());
assertEquals("Bella", patient.name());
    }

    @Test
    void testToString() {
            PatientDto patient = new PatientDto(1L, "Bella", 3, "Golden Retriever", "Female",
                    "12345", "John Doe", "555-1234", 1L, Collections.emptyList());
            String expectedString = "PatientDto[id=1, name=Bella, age=3, breed=Golden Retriever, gender=Female, " +
                    "identificationNumber=12345, guardianName=John Doe, guardianPhone=555-1234, userId=1, appointments=[]]";
            assertEquals(expectedString, patient.toString());
    }

    @Test
    void testUserId() {
        PatientDto patient = new PatientDto(1L, "Bella", 3, "Golden Retriever", "Female",
        "12345", "John Doe", "555-1234", 1L, Collections.emptyList());
assertEquals(1L, patient.userId());
    }
}
