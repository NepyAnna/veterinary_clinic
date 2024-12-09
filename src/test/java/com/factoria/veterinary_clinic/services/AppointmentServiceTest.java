package com.factoria.veterinary_clinic.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import com.factoria.veterinary_clinic.dtos.AppointmentDto;
import com.factoria.veterinary_clinic.enums.AppointmentStatus;
import com.factoria.veterinary_clinic.enums.AppointmentType;
import com.factoria.veterinary_clinic.models.Appointment;
import com.factoria.veterinary_clinic.models.Patient;
import com.factoria.veterinary_clinic.repositories.AppointmentRepository;

@ExtendWith(MockitoExtension.class)
public class AppointmentServiceTest {
    @Mock
    private AppointmentRepository repository;

    @InjectMocks
    private AppointmentService service;

    @Test
    void testFindAll() {
        Patient patient = new Patient(1L, "John Doe");
        Appointment appointment = new Appointment(
                patient,
                LocalDateTime.of(2024, 12, 20, 10, 0),
                AppointmentType.STANDARD,
                "General check-up",
                AppointmentStatus.PENDING);

        when(repository.findAll()).thenReturn(List.of(appointment));

        List<AppointmentDto> result = service.findAll();

        assertEquals(1, result.size());
        assertEquals(appointment.getId(), result.get(0).id());
        assertEquals(patient.getId(), result.get(0).patientId());
        assertEquals("John Doe", result.get(0).patientName());
        verify(repository, times(1)).findAll();
    }

    @Test
    void testFindById() {
        Patient patient = new Patient(1L, "John Doe");
        Appointment appointment = new Appointment(
                patient,
                LocalDateTime.of(2024, 12, 20, 10, 0),
                AppointmentType.STANDARD,
                "General check-up",
                AppointmentStatus.PENDING);

        when(repository.findById(1L)).thenReturn(Optional.of(appointment));

        AppointmentDto result = service.findById(1L);

        assertNotNull(result);
        assertEquals("John Doe", result.patientName());
        verify(repository, times(1)).findById(1L);
    }
}
