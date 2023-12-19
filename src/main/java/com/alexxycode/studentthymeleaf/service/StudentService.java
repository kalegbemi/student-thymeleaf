package com.alexxycode.studentthymeleaf.service;

import com.alexxycode.studentthymeleaf.exceptions.StudentNotFoundException;
import com.alexxycode.studentthymeleaf.model.Student;
import com.alexxycode.studentthymeleaf.repository.StudentRepository;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student saveStudent(Student student){
        return studentRepository.save(student);
    }

    @Cacheable(value = "students")
    public List<Student> allStudent(){
        return studentRepository.findAll();
    }

    public Student updateStudent(Long id, Student student){
        Student toUpdate = getStudentById(id);

        toUpdate.setAddress(student.getAddress());
        toUpdate.setEmail(student.getEmail());
        toUpdate.setLastName(student.getLastName());
        toUpdate.setFirstName(student.getFirstName());
        toUpdate.setPhoneNumber(student.getPhoneNumber());
        toUpdate.setDateOfBirth(student.getDateOfBirth());

        return studentRepository.save(toUpdate);

    }
    @Cacheable(value = "students",key = "#id")
    public Student getStudentById(Long id) {
        return studentRepository.findById(id).orElseThrow(()->new StudentNotFoundException("Student with id ("+id+") does not exists"));
    }

    public String deleteStudent(Long id){
        Student toDelete = getStudentById(id);
        if(toDelete == null){
            return ("student does not exist");
        }
        studentRepository.deleteById(id);
        return "student successfully delete";
    }

}
