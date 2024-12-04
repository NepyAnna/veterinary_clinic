package com.factoria.veterinary_clinic.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.factoria.veterinary_clinic.dtos.AppointmentDto;
import com.factoria.veterinary_clinic.models.Appointment;
import com.factoria.veterinary_clinic.models.Patient;
import com.factoria.veterinary_clinic.repositories.AppointmentRepository;

import jakarta.transaction.Transactional;

@Service
public class AppointmentService {
    private final AppointmentRepository repository;

    public AppointmentService(AppointmentRepository repository) {
        this.repository = repository;
    }

    public List<Appointment> findAll() {
        return repository.findAll();
    }

    @Transactional
    public AppointmentDto createAppointment(AppointmentDto newAppointmentData) {
        Patient patient = repository.findPatientById(newAppointmentData.patientId())
                .orElseThrow(
                        () -> new RuntimeException("Patient id " + newAppointmentData.patientId() + " not found!"));

        Appointment newAppointment = new Appointment(
                patient,
                newAppointmentData.appointmentDateTime(),
                newAppointmentData.type(),
                newAppointmentData.reason(),
                newAppointmentData.status());

        Appointment savedAppointment = repository.save(newAppointment);

        return new AppointmentDto(
                savedAppointment.getPatient().getId(),
                savedAppointment.getPatient().getName(),
                savedAppointment.getAppointmentDateTime(),
                savedAppointment.getType(),
                savedAppointment.getReason(),
                savedAppointment.getStatus());
    }

    public void deleteAppointment(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Appointment with id " + id + " not found");
        }
        repository.deleteById(id);
    }

    @Transactional
    public AppointmentDto updateAppointment(Long id, AppointmentDto updatedData) {
        Appointment existingAppointment = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Appointment with id " + id + " not found"));

        existingAppointment.setAppointmentDateTime(updatedData.appointmentDateTime());
        existingAppointment.setType(updatedData.type());
        existingAppointment.setReason(updatedData.reason());
        existingAppointment.setStatus(updatedData.status());

        Appointment savedAppointment = repository.save(existingAppointment);

        return new AppointmentDto(
                savedAppointment.getPatient().getId(),
                savedAppointment.getPatient().getName(),
                savedAppointment.getAppointmentDateTime(),
                savedAppointment.getType(),
                savedAppointment.getReason(),
                savedAppointment.getStatus());
    }

    @Transactional
    public Optional<Appointment> findById(Long id) {
        return repository.findById(id);
    }
}
