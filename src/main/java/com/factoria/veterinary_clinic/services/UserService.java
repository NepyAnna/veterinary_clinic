package com.factoria.veterinary_clinic.services;
import com.factoria.veterinary_clinic.models.User;
import com.factoria.veterinary_clinic.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
@Service
public class UserService {
  @Autowired
  private UserRepository userRepository;
  public List<User> getAllUsers() {
    return userRepository.findAll();
  }
  public Optional<User> getUserById(Long id) {
    return userRepository.findById(id);
  }
  public User createUser(User user) {
    return userRepository.save(user);
  }
  public User updateUser(Long id, User updatedUser) {
    return userRepository.findById(id).map(user -> {
      user.setName(updatedUser.getName());
      user.setPassword(updatedUser.getPassword());
      user.setEmail(updatedUser.getEmail());
      return userRepository.save(user);
    }).orElseThrow(() -> new RuntimeException("User not found"));
  }
  public void deleteUser(Long id) {
    userRepository.deleteById(id);
  }
}