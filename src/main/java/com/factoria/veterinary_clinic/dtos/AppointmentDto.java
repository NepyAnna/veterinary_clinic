package com.factoria.veterinary_clinic.dtos;

import java.time.LocalDateTime;

public record AppointmentDto(Long patientId,  String patientName, LocalDateTime appointmentDateTime, String type, String reason, String status) {}
