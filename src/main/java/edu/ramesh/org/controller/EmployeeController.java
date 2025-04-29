package edu.ramesh.org.controller;


import edu.ramesh.org.dto.Employee;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/Employee")
@CrossOrigin
public class EmployeeController {

    @PostMapping("/add")
    public ResponseEntity<Employee> addEmployee(@RequestParam Employee employee){
        return null;
    }

}
