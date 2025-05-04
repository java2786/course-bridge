package com.demo;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Embeddable;

@Embeddable
public class EnrollmentId implements Serializable {
    private int studentId;
    private int courseId;

    public EnrollmentId() {
    }

    public EnrollmentId(int studentId, int courseId) {
        this.studentId = studentId;
        this.courseId = courseId;
    }

    // Getters and Setters
    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof EnrollmentId)) return false;
        EnrollmentId enrollId = (EnrollmentId) obj;
        return studentId == enrollId.studentId && courseId == enrollId.courseId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentId, courseId);
    }
}

