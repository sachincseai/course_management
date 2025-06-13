package com.example.coursemanagement.controller;

import com.example.coursemanagement.entity.Course;
import com.example.coursemanagement.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class CourseWebController {

    @Autowired
    private CourseService courseService;

    @GetMapping("/")
    public String home() {
        return "redirect:/courses";
    }

    @GetMapping("/courses")
    public String listCourses(Model model) {
        model.addAttribute("courses", courseService.getAllCourses());
        return "courses/list";
    }

    @GetMapping("/courses/new")
    public String showCreateForm(Model model) {
        model.addAttribute("course", new Course());
        return "courses/form";
    }

    @GetMapping("/courses/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        courseService.getCourseById(id).ifPresent(course ->
                model.addAttribute("course", course));
        return "courses/form";
    }

    @PostMapping("/courses")
    public String saveCourse(@ModelAttribute Course course, RedirectAttributes redirectAttributes) {
        if (course.getId() == null) {
            courseService.saveCourse(course);
            redirectAttributes.addFlashAttribute("message", "Course created successfully!");
        } else {
            courseService.updateCourse(course.getId(), course);
            redirectAttributes.addFlashAttribute("message", "Course updated successfully!");
        }
        return "redirect:/courses";
    }

    @GetMapping("/courses/delete/{id}")
    public String deleteCourse(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        if (courseService.deleteCourse(id)) {
            redirectAttributes.addFlashAttribute("message", "Course deleted successfully!");
        } else {
            redirectAttributes.addFlashAttribute("error", "Course not found!");
        }
        return "redirect:/courses";
    }

    @GetMapping("/courses/instructor/{name}")
    public String coursesByInstructor(@PathVariable String name, Model model) {
        model.addAttribute("courses", courseService.getCoursesByInstructor(name));
        model.addAttribute("instructor", name);
        return "courses/by-instructor";
    }
}
