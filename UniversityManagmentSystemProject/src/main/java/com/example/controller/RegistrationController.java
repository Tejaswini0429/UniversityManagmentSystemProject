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

import com.example.entity.Registration;
import com.example.exception.ResourceNotFoundException;
import com.example.repository.RegistrationRepository;


@RestController
@RequestMapping("/api/registrations")
public class RegistrationController {

 @Autowired
 private RegistrationRepository registrationRepository;

 @GetMapping("/{id}")
 public ResponseEntity<Registration> getRegistrationById(@PathVariable Long id) {
     Registration registration = registrationRepository.findById(id)
             .orElseThrow(() -> new ResourceNotFoundException("Registration not found with id: " + id));
     return ResponseEntity.ok(registration);
 }

 @PostMapping
 public ResponseEntity<Registration> createRegistration(@RequestBody Registration registration) {
     Registration createdRegistration = registrationRepository.save(registration);
     return new ResponseEntity<>(createdRegistration, HttpStatus.CREATED);
 }

 @PutMapping("/{id}")
 public ResponseEntity<Registration> updateRegistration(@PathVariable Long id, @RequestBody Registration registration) {
     registrationRepository.findById(id)
             .orElseThrow(() -> new ResourceNotFoundException("Registration not found with id: " + id));

     registration.setId(id);
     Registration updatedRegistration = registrationRepository.save(registration);
     return ResponseEntity.ok(updatedRegistration);
 }

 @DeleteMapping("/{id}")
 public ResponseEntity<String> deleteRegistration(@PathVariable Long id) {
     registrationRepository.findById(id)
             .orElseThrow(() -> new ResourceNotFoundException("Registration not found with id: " + id));

     registrationRepository.deleteById(id);
     return ResponseEntity.ok("Registration deleted successfully");
 }

 @GetMapping
 public ResponseEntity<List<Registration>> getAllRegistrations() {
     List<Registration> registrations = registrationRepository.findAll();
     return ResponseEntity.ok(registrations);
 }
}