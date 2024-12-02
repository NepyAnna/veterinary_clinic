package com.factoria.veterinary_clinic.dtos;

import java.time.LocalDateTime;

import com.factoria.veterinary_clinic.enums.AppointmentStatus;
import com.factoria.veterinary_clinic.enums.AppointmentType;

public record AppointmentDto(Long patientId,  String patientName, LocalDateTime appointmentDateTime, AppointmentType type, String reason, AppointmentStatus status) {}
