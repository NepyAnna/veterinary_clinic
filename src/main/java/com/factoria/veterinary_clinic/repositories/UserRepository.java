package com.factoria.veterinary_clinic.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.factoria.veterinary_clinic.models.User;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);
}
