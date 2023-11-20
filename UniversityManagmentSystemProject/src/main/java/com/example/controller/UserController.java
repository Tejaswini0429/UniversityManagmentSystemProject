package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.User;
import com.example.exception.ResourceNotFoundException;
import com.example.repository.UserRepository;

@RestController
@RequestMapping("/api/users")
public class UserController {

 @Autowired
 private UserRepository userRepository;

 @GetMapping("/{id}")
 public ResponseEntity<User> getUserById(@PathVariable Long id) {
     User user = userRepository.findById(id)
             .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
     return ResponseEntity.ok(user);
 }

 @PostMapping
 public ResponseEntity<User> createUser(@RequestBody User user) {
     User createdUser = userRepository.save(user);
     return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
 }

 @PutMapping("/{id}")
 public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User user) {
     userRepository.findById(id)
             .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));

     user.setId(id);
     User updatedUser = userRepository.save(user);
     return ResponseEntity.ok(updatedUser);
 }

 @DeleteMapping("/{id}")
 public ResponseEntity<String> deleteUser(@PathVariable Long id) {
     userRepository.findById(id)
             .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));

     userRepository.deleteById(id);
     return ResponseEntity.ok("User deleted successfully");
 }

 @GetMapping
 public ResponseEntity<List<User>> getAllUsers() {
     List<User> users = userRepository.findAll();
     return ResponseEntity.ok(users);
 }
}
