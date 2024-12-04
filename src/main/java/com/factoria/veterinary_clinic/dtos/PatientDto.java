package com.factoria.veterinary_clinic.dtos;


public record PatientDto(Long patientId, String name, int age, String breed, String gender, String identificationNumber, String GuardianName,String guardianPhone) {


}
