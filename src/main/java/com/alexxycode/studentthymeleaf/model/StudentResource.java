package com.alexxycode.studentthymeleaf.model;

import lombok.Data;
import org.springframework.hateoas.RepresentationModel;
@Data
public class StudentResource extends RepresentationModel<StudentResource> {

    private Student student;

}
