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

import com.example.entity.College;
import com.example.exception.ResourceNotFoundException;
import com.example.repository.CollegeRepository;

@RestController
@RequestMapping("/api/colleges")
public class CollegeController {

 @Autowired
 private CollegeRepository collegeRepository;

 @GetMapping("/{id}")
 public ResponseEntity<College> getCollegeById(@PathVariable Long id) {
     College college = collegeRepository.findById(id)
             .orElseThrow(() -> new ResourceNotFoundException("College not found with id: " + id));
     return ResponseEntity.ok(college);
 }

 @PostMapping
 public ResponseEntity<College> createCollege(@RequestBody College college) {
     College createdCollege = collegeRepository.save(college);
     return new ResponseEntity<>(createdCollege, HttpStatus.CREATED);
 }

 @PutMapping("/{id}")
 public ResponseEntity<College> updateCollege(@PathVariable Long id, @RequestBody College college) {
     collegeRepository.findById(id)
             .orElseThrow(() -> new ResourceNotFoundException("College not found with id: " + id));

     college.setId(id);
     College updatedCollege = collegeRepository.save(college);
     return ResponseEntity.ok(updatedCollege);
 }

 @DeleteMapping("/{id}")
 public ResponseEntity<String> deleteCollege(@PathVariable Long id) {
     collegeRepository.findById(id)
             .orElseThrow(() -> new ResourceNotFoundException("College not found with id: " + id));

     collegeRepository.deleteById(id);
     return ResponseEntity.ok("College deleted successfully");
 }

 @GetMapping
 public ResponseEntity<List<College>> getAllColleges() {
     List<College> colleges = collegeRepository.findAll();
     return ResponseEntity.ok(colleges);
 }
}
