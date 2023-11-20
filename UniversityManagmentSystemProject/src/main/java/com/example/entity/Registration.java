package com.example.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Registration {
	
 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 private Long id;

 @ManyToOne
 @JoinColumn(name = "student_id")
 private Student student;

 @ManyToOne
 @JoinColumn(name = "course_id")
 private Course course;

public Long getId() {
	return id;
}

public void setId(Long id) {
	this.id = id;
}



}
