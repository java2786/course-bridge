package com.demo.services;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.entities.Student;
import com.demo.repositories.StudentRepository;

@Service
public class StudentService {

	private static final Logger logger = LoggerFactory.getLogger(StudentService.class);
	
 	@Autowired
	private StudentRepository stdRepo;
	
	public List<Student> allStudents(){
		logger.info("Retrieving all students");
		return stdRepo.findAll();
	}
	
	public Student saveStudent(Student std){
		logger.info("Student request body: "+ std);
		return stdRepo.save(std);
	}
	
	public Student getStudentById(int id) throws Exception {
		logger.info("Fetching student with id: #"+ id);
		Optional<Student> stdOptional = stdRepo.findById(id);
		if(stdOptional.isEmpty()) {
			logger.error("Student with id: #"+ id +"not found");
			throw new Exception("Student not found");
		} else {
			Student student = stdOptional.get();
			return student;
		}
	}
	
	public Student deleteStudentById(int id) throws Exception{
		logger.info("Deleting student with id: #"+ id);
		Student std = getStudentById(id);
		stdRepo.deleteById(id);
		return std;
	}

	public Student updateStudentById(int id, Student input) throws Exception {
		logger.info("Updating student with id: #"+ id);
		Student std = getStudentById(id);
		if(input.getName()!=null && input.getName().length()>0)
		std.setName(input.getName());

		if(input.getEmail()!=null && input.getEmail().length()>0)
		std.setEmail(input.getEmail());

		return stdRepo.save(std);
	}

	public List<Student> findByName(String name) {
		logger.info("Searching students by name: #"+ name);
		return stdRepo.findByName(name);
	}   
}
