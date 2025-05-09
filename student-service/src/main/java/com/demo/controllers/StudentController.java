package com.demo.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.demo.entities.Student;
import com.demo.services.StudentService;

import jakarta.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
@RequestMapping("/students")
@ResponseBody
//@CrossOrigin("*")
public class StudentController {

	private static final Logger logger = LoggerFactory.getLogger(StudentController.class);

	@Autowired
	private StudentService stdService;
	
	@GetMapping()
	public List<Student> allStudents(){
		logger.info("Retrieving all students");
		return stdService.allStudents();
	}
	
	@PostMapping()
	public Student saveStudent(@Valid @RequestBody Student std){
		logger.info("Student request body: "+ std);
		return stdService.saveStudent(std);
	}
	
	@GetMapping("/{id}")
	public Student getStudentById(@PathVariable int id) throws Exception {
		logger.info("Fetching student with id: #"+ id);
		return stdService.getStudentById(id);
	}

	@DeleteMapping("/{id}")
	public Student deleteStudentById(@PathVariable int id) throws Exception {
		logger.info("Deleting student with id: #"+ id);
		return stdService.deleteStudentById(id);
	}

	@PutMapping("/{id}")
	public Student updateStudentById(@PathVariable int id, @Valid @RequestBody Student input) throws Exception {
		logger.info("Updating student with id: #"+ id);
		return stdService.updateStudentById(id, input);
	}


	// GET http://localhost:5555/students/search?name=Kamlesh
	@GetMapping("/search")
	public List<Student> findByName(@RequestParam("name") String name) {
		logger.info("Searching students by name: #"+ name);
		return stdService.findByName(name);
	}
}
