package edu.ramesh.org.service.impl;

import edu.ramesh.org.dao.EmployeeRepository;
import edu.ramesh.org.dto.Employee;
import edu.ramesh.org.entity.EmployeeEntity;
import edu.ramesh.org.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final ModelMapper mapper;

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll()
                .stream()
                .map(entity -> mapper.map(entity, Employee.class))
                .toList();
    }

    @Override
    public Employee getEmployeeById(Long id) {
        return null;
    }

    @Override
    public Employee createEmployee(Employee employee) {
        if (employeeRepository.existsByEmail(employee.getEmail())) {
            throw new RuntimeException("Email already exists");
        }
        return  mapper.map(employeeRepository.save(mapper.map(employee, EmployeeEntity.class)),Employee.class);
    }

    @Override
    public Employee updateEmployee(Long id, Employee employeeDetails) {
        return null;
    }

    @Override
    public void deleteEmployee(Long id) {

    }
}
