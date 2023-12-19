package com.alexxycode.studentthymeleaf.model;

import jakarta.persistence.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
//@RequiredArgsConstructor
@Builder
@Entity
@Table(name = "student", uniqueConstraints = @UniqueConstraint(columnNames = "email"))

public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    private String email;

    @Column(name = "dob")
    private String dateOfBirth;

    @Column(name = "phone_number")
    private String phoneNumber;

    private String address;

}
