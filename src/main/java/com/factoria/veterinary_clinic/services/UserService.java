package com.factoria.veterinary_clinic.services;

import com.factoria.veterinary_clinic.models.User;
import com.factoria.veterinary_clinic.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private PasswordEncoder passwordEncoder;

  public List<User> getAllUsers() {
    return userRepository.findAll();
  }

  public Optional<User> getUserById(Long id) {
    return userRepository.findById(id);
  }

  public User createUser(User user) {
    user.setPassword(passwordEncoder.encode(user.getPassword()));
    return userRepository.save(user);
  }

  public User updateUser(Long id, User updatedUser) {
    return userRepository.findById(id).map(user -> {
      user.setName(updatedUser.getName());

      if (updatedUser.getPassword() != null && !updatedUser.getPassword().isEmpty()) {
        user.setPassword(passwordEncoder.encode(updatedUser.getPassword()));
      } else {

        user.setPassword(user.getPassword());
      }

      user.setEmail(updatedUser.getEmail());
      return userRepository.save(user);
    }).orElseThrow(() -> new RuntimeException("User not found"));
  }

  public void deleteUser(Long id) {
    userRepository.deleteById(id);
  }

  public Optional<User> getUserByEmail(String email) {
    return userRepository.findByEmail(email);
  }
}
