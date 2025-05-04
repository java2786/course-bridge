package com.demo;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("COURSE-BRIDGE-COURSE-SERVICE")
public interface CourseClient {

    @GetMapping("/courses/{id}")
    public Course getCourseById(@PathVariable int id);

}
