package com.demo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="courses")
public class Course {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(unique = true)
	private String name;
	private String description;
	private String instructor;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getInstructor() {
		return instructor;
	}
	public void setInstructor(String instructor) {
		this.instructor = instructor;
	}
	@Override
	public String toString() {
		return "Course [id=" + id + ", name=" + name + ", description=" + description + ", instructor=" + instructor
				+ "]";
	}
	public Course() {
		super();
	}
	public Course(String name, String description, String instructor) {
		super();
		this.name = name;
		this.description = description;
		this.instructor = instructor;
	}
	public Course(int id, String name, String description, String instructor) {
		this(name, description, instructor);
		this.id = id;
	}
	
	
}
