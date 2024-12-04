package com.factoria.veterinary_clinic.dtos;

import java.util.List;

public record PatientDto(
                Long id,
                String name,
                int age,
                String breed,
                String gender,
                String identificationNumber,
                String guardianName,
                String guardianPhone,
                Long userId,
                List<AppointmentDto> appointments) {
}
