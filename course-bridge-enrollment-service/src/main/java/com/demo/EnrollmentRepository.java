package com.demo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface EnrollmentRepository extends JpaRepository<Enrollment, Integer>{

//	@Query(value="select count(*) from enrollments", nativeQuery = true)
	@Query(value="select count(*) from Enrollment e", nativeQuery = false)
	public int countEnrollments();

	
	@Query(value="select e from Enrollment e where e.id.studentId=?1", nativeQuery = false)
	public List<Enrollment> getEnrollmentOfStudent(int studentId);
}
