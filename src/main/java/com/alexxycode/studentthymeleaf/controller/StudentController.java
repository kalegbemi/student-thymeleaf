package com.alexxycode.studentthymeleaf.controller;

import com.alexxycode.studentthymeleaf.model.Student;
import com.alexxycode.studentthymeleaf.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.awt.print.Pageable;
import java.util.List;

@RestController
public class StudentController {
    @Autowired
    private StudentService studentService;

    @PostMapping("/save-student")
    public ModelAndView saveStudent(@ModelAttribute Student student){
       studentService.saveStudent(student);
        //ModelAndView modelAndView = new ModelAndView("add-student");
       // return "redirect:/home";
        return studentList();
    }

   @GetMapping("/add-student")
   public ModelAndView saveStudent(){
       ModelAndView modelAndView = new ModelAndView("add-student");
       Student student = new Student();
       modelAndView.addObject("student", student);
       return modelAndView;
   }

   @GetMapping(value = {"/student-list","","/home"})
    public ModelAndView studentList(){
        ModelAndView modelAndView = new ModelAndView("student-list");
                List<Student> studentList = studentService.allStudent();
        modelAndView.addObject("students",studentList);
        return modelAndView;
   }

    @GetMapping("/updatestudent")
    public ModelAndView updateStudent(@RequestParam Long studentid){
        ModelAndView modelAndView = new ModelAndView("add-student");
        Student student = studentService.getStudentById(studentid);
        modelAndView.addObject("student", student);
        return modelAndView;
    }

    @GetMapping("/deletestudent")
    public ModelAndView deleteStudent(@RequestParam Long studentid){
        studentService.deleteStudent(studentid);
        return studentList();
    }

    @GetMapping("studentpage")
    public Page<Student> pagedstudent(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size){
        return studentService.pagedstudent(page, size);
    }


}
