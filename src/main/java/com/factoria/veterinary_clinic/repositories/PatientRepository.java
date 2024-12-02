package com.factoria.veterinary_clinic.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.factoria.veterinary_clinic.models.Patient;

@Repository
public interface PatientRepository extends JpaRepository<Patient,Long> {
  
}

