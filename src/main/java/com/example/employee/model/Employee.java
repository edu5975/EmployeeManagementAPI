package com.example.employee.model;

import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Past;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import java.time.LocalDate;

@Data
@Document(collection = "employees")
public class Employee {

    @Id
    private String id;

    @NotBlank
    @Size(min = 2, max = 50)
    private String firstName;

    @NotBlank
    @Size(min = 2, max = 50)
    private String middleName;

    @NotBlank
    @Size(min = 2, max = 50)
    private String lastName;

    private String maidenName;

    private Integer age;

    private String gender;

    @Past
    private LocalDate birthDate;

    @NotBlank
    private String jobTitle;
}
