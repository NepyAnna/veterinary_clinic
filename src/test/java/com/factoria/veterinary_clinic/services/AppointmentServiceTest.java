package com.factoria.veterinary_clinic.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
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

    @Test
    void testDeleteAppointment() {
        when(repository.existsById(1L)).thenReturn(true);

        service.deleteAppointment(1L);

        verify(repository, times(1)).existsById(1L);
        verify(repository, times(1)).deleteById(1L);
    }

    @Test
    void testUpdateAppointment() {
        Patient patient = new Patient(1L, "John Doe");
        Appointment existingAppointment = new Appointment();
        existingAppointment.setPatient(patient);
        existingAppointment.setAppointmentDateTime(LocalDateTime.of(2024, 12, 20, 10, 0));
        existingAppointment.setType(AppointmentType.STANDARD);
        existingAppointment.setReason("General check-up");
        existingAppointment.setStatus(AppointmentStatus.PENDING);

        /*AppointmentDto updatedDto = new AppointmentDto(existingAppointment);

        when(repository.findById(1L)).thenReturn(Optional.of(existingAppointment));
        when(repository.save(any(Appointment.class))).thenReturn(existingAppointment);

        AppointmentDto result = service.updateAppointment(1L, updatedDto);

        assertNotNull(result);
        assertEquals("FollowUp", result.type());
        assertEquals("Updated reason", result.reason());
        assertEquals("Completed", result.status());
        verify(repository, times(1)).findById(1L);
        verify(repository, times(1)).save(any(Appointment.class));*/
    }
}
