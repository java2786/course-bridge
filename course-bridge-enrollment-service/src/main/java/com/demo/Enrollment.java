package com.demo;

import java.time.LocalDate;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name="enrollments")
public class Enrollment {

	// @Id
	// @GeneratedValue(strategy = GenerationType.IDENTITY)
	// private int id;
	
	// private int studentId; // Reference to Student in Student Service
    // private int courseId;  // Reference to Course in Course Service

	@EmbeddedId
    private EnrollmentId id;

	private LocalDate enrollmentDate;

	public EnrollmentId getId() {
		return id;
	}

	public void setId(EnrollmentId id) {
		this.id = id;
	}

	public LocalDate getEnrollmentDate() {
		return enrollmentDate;
	}

	public void setEnrollmentDate(LocalDate enrollmentDate) {
		this.enrollmentDate = enrollmentDate;
	}

	@Override
	public String toString() {
		return "Enrollment [id=" + id + ", enrollmentDate=" + enrollmentDate + "]";
	}
	
}
