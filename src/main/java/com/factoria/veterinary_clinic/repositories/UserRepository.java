package com.factoria.veterinary_clinic.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.factoria.veterinary_clinic.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
