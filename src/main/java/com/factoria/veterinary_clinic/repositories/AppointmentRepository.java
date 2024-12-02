package com.factoria.veterinary_clinic.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.factoria.veterinary_clinic.models.Appointment;
import com.factoria.veterinary_clinic.models.Patient;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
     @Query("SELECT p FROM Patient p WHERE p.id = :id")
    Optional<Patient> findPatientById(@Param("id") Long id);
}
