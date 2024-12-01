package com.factoria.veterinary_clinic.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.factoria.veterinary_clinic.models.Appointment;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {}
