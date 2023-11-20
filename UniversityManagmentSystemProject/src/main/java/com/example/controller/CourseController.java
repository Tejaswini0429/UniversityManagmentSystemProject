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

import com.example.entity.Course;
import com.example.exception.ResourceNotFoundException;
import com.example.repository.CourseRepository;

@RestController
@RequestMapping("/api/courses")
public class CourseController {

 @Autowired
 private CourseRepository courseRepository;

 @GetMapping("/{id}")
 public ResponseEntity<Course> getCourseById(@PathVariable Long id) {
     Course course = courseRepository.findById(id)
             .orElseThrow(() -> new ResourceNotFoundException("Course not found with id: " + id));
     return ResponseEntity.ok(course);
 }

 @PostMapping
 public ResponseEntity<Course> createCourse(@RequestBody Course course) {
     Course createdCourse = courseRepository.save(course);
     return new ResponseEntity<>(createdCourse, HttpStatus.CREATED);
 }

 @PutMapping("/{id}")
 public ResponseEntity<Course> updateCourse(@PathVariable Long id, @RequestBody Course course) {
     courseRepository.findById(id)
             .orElseThrow(() -> new ResourceNotFoundException("Course not found with id: " + id));

     course.setId(id);
     Course updatedCourse = courseRepository.save(course);
     return ResponseEntity.ok(updatedCourse);
 }

 @DeleteMapping("/{id}")
 public ResponseEntity<String> deleteCourse(@PathVariable Long id) {
     courseRepository.findById(id)
             .orElseThrow(() -> new ResourceNotFoundException("Course not found with id: " + id));

     courseRepository.deleteById(id);
     return ResponseEntity.ok("Course deleted successfully");
 }

 @GetMapping
 public ResponseEntity<List<Course>> getAllCourses() {
     List<Course> courses = courseRepository.findAll();
     return ResponseEntity.ok(courses);
 }
}
