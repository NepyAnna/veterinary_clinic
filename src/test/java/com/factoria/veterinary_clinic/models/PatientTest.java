package com.factoria.veterinary_clinic.models;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class PatientTest {

    @Test
    void testGetAge() {
            Patient patient = new Patient();
            patient.setAge(5);
            assertEquals(5, patient.getAge());
    }

    @Test
    void testGetAppointments() {
        Appointment appointment = new Appointment();
        List<Appointment> appointments = new ArrayList<>();
        appointments.add(appointment);

        Patient patient = new Patient();
        patient.setAppointments(appointments);

        assertEquals(1, patient.getAppointments().size());
        assertEquals(appointment, patient.getAppointments().get(0));
    }

    @Test
    void testGetBreed() {
        Patient patient = new Patient();
        patient.setBreed("Golden Retriever");
        assertEquals("Golden Retriever", patient.getBreed());
    }

    @Test
    void testGetGender() {
        Patient patient = new Patient();
        patient.setGender("Female");
        assertEquals("Female", patient.getGender());
    }

    @Test
    void testGetGuardianName() {
            Patient patient = new Patient();
            patient.setGuardianName("John Doe");
            assertEquals("John Doe", patient.getGuardianName());
    }

    @Test
    void testGetGuardianPhone() {
        Patient patient = new Patient();
        patient.setGuardianPhone("555-1234");
        assertEquals("555-1234", patient.getGuardianPhone());
    }

    @Test
    void testGetId() {
        Patient patient = new Patient();
        patient.setId_patient(1L);
        assertEquals(1L, patient.getId());
    }

    @Test
    void testGetIdentificationNumber() {
        Patient patient = new Patient();
        patient.setIdentificationNumber("12345");
        assertEquals("12345", patient.getIdentificationNumber());
    }

    @Test
    void testGetName() {
        Patient patient = new Patient();
        patient.setName("Bella");
        assertEquals("Bella", patient.getName());
    }

    @Test
    void testGetUser() {
        User user = new User();
        Patient patient = new Patient();
        patient.setUser(user);
        assertEquals(user, patient.getUser());
    }

    @Test
    void testSetAge() {
        Patient patient = new Patient();
        patient.setAge(3);
        assertEquals(3, patient.getAge());
    }

    @Test
    void testSetAppointments() {
        Appointment appointment = new Appointment();
        List<Appointment> appointments = new ArrayList<>();
        appointments.add(appointment);

        Patient patient = new Patient();
        patient.setAppointments(appointments);

        assertEquals(appointments, patient.getAppointments());
    }

    @Test
    void testSetBreed() {
        Patient patient = new Patient();
        patient.setBreed("Labrador");
        assertEquals("Labrador", patient.getBreed());
    }

    @Test
    void testSetGender() {
        Patient patient = new Patient();
        patient.setGender("Male");
        assertEquals("Male", patient.getGender());
    }

    @Test
    void testSetGuardianName() {
        Patient patient = new Patient();
        patient.setGuardianName("Jane Smith");
        assertEquals("Jane Smith", patient.getGuardianName());
    }

    @Test
    void testSetGuardianPhone() {
        Patient patient = new Patient();
        patient.setGuardianPhone("555-5678");
        assertEquals("555-5678", patient.getGuardianPhone());
    }

    @Test
    void testSetId_patient() {
        Patient patient = new Patient();
        patient.setId_patient(2L);
        assertEquals(2L, patient.getId());
    }

    @Test
    void testSetIdentificationNumber() {
        Patient patient = new Patient();
        patient.setIdentificationNumber("67890");
        assertEquals("67890", patient.getIdentificationNumber());
    }

    @Test
    void testSetName() {
        Patient patient = new Patient();
        patient.setName("Charlie");
        assertEquals("Charlie", patient.getName());
    }

    @Test
    void testSetUser() {
        User user = new User();
        Patient patient = new Patient();
        patient.setUser(user);
        assertEquals(user, patient.getUser());
    }
}
