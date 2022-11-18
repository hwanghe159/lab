package com.example.springdatajpa.controller;

import com.example.springdatajpa.domain.User;
import com.example.springdatajpa.domain.UserAddressRepository;
import com.example.springdatajpa.domain.UserRepository;
import com.example.springdatajpa.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {

  private final UserRepository userRepository;
  private final UserService userService;

  @PostMapping("/user")
  public ResponseEntity<?> createUser(@RequestBody String name) {
    userService.saveUser(name);
    return ResponseEntity.ok().build();
  }

  @PostMapping("/user/{userId}/address")
  public ResponseEntity<?> createAddress(@PathVariable Long userId, @RequestBody String address) {
    userService.addAddress(userId, address);
    return ResponseEntity.ok().build();
  }

  @DeleteMapping("/user/{userId}/address/{addressId}")
  public ResponseEntity<?> deleteAddress(@PathVariable Long userId, @PathVariable Long addressId) {
    userService.removeAddress(userId, addressId);
    return ResponseEntity.ok().build();
  }
}
