package com.factoria.veterinary_clinic.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.factoria.veterinary_clinic.models.User;

public interface UserRepository extends JpaRepository<User, Long> {}
