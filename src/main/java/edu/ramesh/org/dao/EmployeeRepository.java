package edu.ramesh.org.dao;


import edu.ramesh.org.entity.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeEntity,Long> {
    boolean existsByEmail(String email);

    @Query("SELECT e FROM EmployeeEntity e WHERE e.createdAt BETWEEN :startDate AND :endDate")
    List<EmployeeEntity> findEmployeesCreatedBetweenDates(LocalDateTime startDate, LocalDateTime endDate);

    List<EmployeeEntity> findByCreatedAtAfter(LocalDateTime date);
}
