package edu.ramesh.org.dto;

import edu.ramesh.org.util.DepartmentType;

import java.time.LocalDateTime;

public class Employee {
    private Long id;
    private String name;
    private String email;
    private DepartmentType department;
    private LocalDateTime createdAt;
    private LocalDateTime updateAt;
}
