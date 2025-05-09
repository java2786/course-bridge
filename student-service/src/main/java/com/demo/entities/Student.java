package com.demo.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "students")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull(message = "Name is required")
    @NotEmpty(message = "Name cannot be empty")
    @Size(min = 3, max = 10, message = "Name must be between 3 and 10 characters")
    private String name;

    @NotNull(message = "Email is required")
    @NotEmpty(message = "Email cannot be empty")
    @Column(unique = true)
    @Email(message = "Email must be a valid email address")
    @CustomUniqueEmail(message = "Email must end with @gmail.com")
    private String email;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Student [id=" + id + ", name=" + name + ", email=" + email + "]";
    }

    public Student() {
        super();
    }

    public Student(String name, String email) {
        super();
        this.name = name;
        this.email = email;
    }

    public Student(int id, String name, String email) {
        super();
        this.id = id;
        this.name = name;
        this.email = email;
    }
}
