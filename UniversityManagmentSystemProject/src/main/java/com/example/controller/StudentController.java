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

import com.example.entity.Student;
import com.example.exception.ResourceNotFoundException;
import com.example.repository.StudentRepository;

@RestController
@RequestMapping("/api/students")
public class StudentController {

 @Autowired
 private StudentRepository studentRepository;

 @GetMapping("/{id}")
 public ResponseEntity<Student> getStudentById(@PathVariable Long id) {
     Student student = studentRepository.findById(id)
             .orElseThrow(() -> new ResourceNotFoundException("Student not found with id: " + id));
     return ResponseEntity.ok(student);
 }

 @PostMapping
 public ResponseEntity<Student> createStudent(@RequestBody Student student) {
     Student createdStudent = studentRepository.save(student);
     return new ResponseEntity<>(createdStudent, HttpStatus.CREATED);
 }

 @PutMapping("/{id}")
 public ResponseEntity<Student> updateStudent(@PathVariable Long id, @RequestBody Student student) {
     studentRepository.findById(id)
             .orElseThrow(() -> new ResourceNotFoundException("Student not found with id: " + id));

     student.setId(id);
     Student updatedStudent = studentRepository.save(student);
     return ResponseEntity.ok(updatedStudent);
 }

 @DeleteMapping("/{id}")
 public ResponseEntity<String> deleteStudent(@PathVariable Long id) {
     studentRepository.findById(id)
             .orElseThrow(() -> new ResourceNotFoundException("Student not found with id: " + id));

     studentRepository.deleteById(id);
     return ResponseEntity.ok("Student deleted successfully");
 }

 @GetMapping
 public ResponseEntity<List<Student>> getAllStudents() {
     List<Student> students = studentRepository.findAll();
     return ResponseEntity.ok(students);
 }
}
