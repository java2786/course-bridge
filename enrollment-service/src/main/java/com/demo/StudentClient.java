package com.demo;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("COURSE-BRIDGE-STUDENT-SERVICE")
public interface StudentClient {

    @GetMapping("/students/{id}")
    public Student getStudentById(@PathVariable int id);

}
