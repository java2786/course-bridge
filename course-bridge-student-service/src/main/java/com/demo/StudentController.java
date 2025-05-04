package com.demo;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/students")
@ResponseBody
//@CrossOrigin("*")
public class StudentController {

	@Autowired
	private StudentRepository stdRepo;
	
	@GetMapping()
	public List<Student> allStudents(){
		return stdRepo.findAll();
	}
	
	@PostMapping()
	public Student saveStudent(@RequestBody Student std){
		return stdRepo.save(std);
	}
	
	@GetMapping("/{id}")
	public Student getStudentById(@PathVariable int id) {
		System.out.println("stdId: "+id);
		Optional<Student> stdOptional = stdRepo.findById(id);
		if(stdOptional.isEmpty()) {
			throw new RuntimeException("Student not found");
		} else {
			Student student = stdOptional.get();
			return student;
		}
	}

	
}
