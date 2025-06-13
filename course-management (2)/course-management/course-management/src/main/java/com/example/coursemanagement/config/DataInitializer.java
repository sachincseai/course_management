package com.example.coursemanagement.config;

import com.example.coursemanagement.entity.Course;
import com.example.coursemanagement.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private CourseRepository courseRepository;

    @Override
    public void run(String... args) throws Exception {
        courseRepository.save(new Course("Java Programming", 40, "John Doe"));
        courseRepository.save(new Course("Spring Boot", 30, "Jane Smith"));
        courseRepository.save(new Course("React Development", 35, "John Doe"));
        courseRepository.save(new Course("Database Design", 25, "Bob Johnson"));
        courseRepository.save(new Course("Web Development", 45, "Jane Smith"));
    }
}
