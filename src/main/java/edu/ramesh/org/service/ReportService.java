package edu.ramesh.org.service;


import org.springframework.core.io.Resource;

public interface ReportService {
    Resource generateLastMonthReport();
    Resource generateLast10DaysEmployeeReport();
}
