package com.example.springdatajpa.service;

import com.example.springdatajpa.domain.User;
import com.example.springdatajpa.domain.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

  private final UserRepository userRepository;

  public void saveUser(String name) {
    userRepository.save(User.builder().name(name).build());
  }

  public void addAddress(Long userId, String address) {
    User user = userRepository.findById(userId).orElseThrow(RuntimeException::new);
    user.addAddress(address);
  }

  public void removeAddress(Long userId, Long addressId) {
    User user = userRepository.findById(userId).orElseThrow(RuntimeException::new);
    user.removeAddress(addressId);
  }
}
