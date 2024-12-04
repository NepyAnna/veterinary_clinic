package com.factoria.veterinary_clinic.dtos;

public record PatientDto(
        String name,
        int age,
        String breed,
        String gender,
        String identificationNumber,
        String guardianName,
        String guardianPhone,
        Long userId) {
}
