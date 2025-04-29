package edu.ramesh.org.service.impl;

import edu.ramesh.org.dao.EmployeeRepository;
import edu.ramesh.org.service.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class ReportServiceImpl implements ReportService {

    private final EmployeeRepository employeeRepository;


    @Override
    public Resource generateLastMonthReport() {
        return null;
    }

    @Override
    public Resource generateLast10DaysEmployeeReport() {
        return null;
    }
}


