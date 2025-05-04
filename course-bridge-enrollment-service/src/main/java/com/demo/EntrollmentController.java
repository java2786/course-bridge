package com.demo;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("enrollments")
//@CrossOrigin("*")
public class EntrollmentController {
	@Autowired
	private StudentClient stdClient;
	@Autowired
	private CourseClient courseclient;
	@Autowired
	private EnrollmentRepository enrollRepo;
	
	@GetMapping("/hello")
	public Student getStd(){
		Student student = null;
		int stdId = 1;
		System.out.println("stdId: "+stdId);
		student = stdClient.getStudentById(stdId);
		System.out.println("student with id #"+stdId+" found: "+student);
		return student;
	}
	
	@PostMapping()
	public Enrollment enrollStudentWithCourse(@RequestBody Map<String, Integer> requestData) {
		System.out.println(requestData);
		Student student = null;
		int stdId = requestData.get("studentId");
		System.out.println("stdId: "+stdId);
		student = stdClient.getStudentById(stdId);
		System.out.println("student with id #"+stdId+" found: "+student);
		Course course = null;
		int courseId = requestData.get("courseId");
		System.out.println("courseId: "+courseId);
		course = courseclient.getCourseById(courseId);
		
		System.out.println(student);
		System.out.println(course);

		if(student ==null || course==null){
			System.out.println("Student or course not found");
			return null;
		}
		
		Enrollment enrollment = new Enrollment();
		// enrollment.setCourseId(course.getId());
		// enrollment.setStudentId(student.getId());

		enrollment.setId(new EnrollmentId(student.getId(), course.getId()));
		enrollment.setEnrollmentDate(LocalDate.now());

		return enrollRepo.save(enrollment);
	}
	
	@GetMapping()
	public List<Enrollment> getEnrollments(){
		System.out.println("finding all enrollments from database");
		return enrollRepo.findAll();
	}
	
	@DeleteMapping("/{id}")
	public Enrollment deleteEntrollment(@PathVariable int id) {
		Enrollment enrollment = getEnrollment(id);
		if(enrollment!=null) {
			enrollRepo.deleteById(id);
		}
		return enrollment;
	}
	
	public Enrollment getEnrollment(int id) {
		System.out.println("finding enrollment from database where id is "+id);
		Optional<Enrollment> optional = enrollRepo.findById(id);
		if(optional.isPresent()) {
			return optional.get();
		} else {
			return null;
		}
	}
	
	@GetMapping("/count")
	public String getEnrollmentsCount() {
		int totalEnrollments = enrollRepo.countEnrollments();
		return "Total "+totalEnrollments+" enrollments found in db";
	}
	
	
	@GetMapping("/byStudent/{id}")
	public List<Enrollment> getEnrollmentsByStudentId(@PathVariable int id){
		return enrollRepo.getEnrollmentOfStudent(id);
	}
}
