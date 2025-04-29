package edu.ramesh.org.dao;

import edu.ramesh.org.dto.Employee;
import edu.ramesh.org.entity.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeEntity,Long> {
    boolean existsByEmail(String email);
}
