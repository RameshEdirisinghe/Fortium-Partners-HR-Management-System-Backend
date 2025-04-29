package edu.ramesh.org.dto;

import edu.ramesh.org.util.DepartmentType;
import edu.ramesh.org.validation.UniqueEmail;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Employee {
    private Long id;
    @NotBlank(message = "Name is mandatory")
    @Size(min = 2, max = 100, message = "Name must be between 2 and 100 characters")
    @Pattern(regexp = "^[a-zA-Z\\s]+$", message = "Name must contain only alphabetic characters and spaces")
    private String name;
    @NotBlank(message = "Email is mandatory")
    @Email(message = "Email should be valid")
    @UniqueEmail(message = "Email must be unique")
    private String email;
    @NotBlank(message = "Email is mandatory")
    @Email(message = "Email should be valid")
    private DepartmentType department;
    @Null(message = "Created timestamp must not be provided, it will be auto-set")
    private LocalDateTime createdAt;
    @Null(message = "Updated timestamp must not be provided, it will be auto-updated")
    private LocalDateTime updateAt;
}
