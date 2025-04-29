package edu.ramesh.org.service.impl;

import edu.ramesh.org.dao.EmployeeRepository;
import edu.ramesh.org.entity.EmployeeEntity;
import edu.ramesh.org.service.ReportService;
import lombok.RequiredArgsConstructor;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ReportServiceImpl implements ReportService {

    private final EmployeeRepository employeeRepository;

    @Override
    public String exportReport(String reportFormat, String period) {
        LocalDateTime now = LocalDateTime.now();
        List<EmployeeEntity> employees;
        String reportName;

        try {
            if ("last10days".equalsIgnoreCase(period)) {
                LocalDateTime startDate = now.minusDays(10);
                employees = employeeRepository.findEmployeesCreatedAfter(startDate);
                reportName = "employees_last_10_days";
            } else if ("last30days".equalsIgnoreCase(period)) {
                LocalDateTime startDate = now.minusDays(30);
                employees = employeeRepository.findEmployeesCreatedBetween(startDate, now);
                reportName = "employees_last_30_days";
            } else {
                employees = employeeRepository.findAll();
                reportName = "employees_all";
            }

            // Load the report template
            File file = ResourceUtils.getFile("classpath:employees.jrxml");
            JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());

            // Create data source
            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(employees);

            // Add parameters
            Map<String, Object> parameters = new HashMap<>();
            parameters.put("createdBy", "Your Company");
            parameters.put("reportPeriod", getReportPeriodDescription(period));

            // Fill the report
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);

            // Export to the requested format
            String outputPath = "generated-reports";
            new File(outputPath).mkdirs();

            String outputFile = outputPath + "/" + reportName + "." + reportFormat.toLowerCase();


            if (reportFormat.equalsIgnoreCase("pdf")) {
                JasperExportManager.exportReportToPdfFile(jasperPrint, outputFile);
            } else if (reportFormat.equalsIgnoreCase("html")) {
                JasperExportManager.exportReportToHtmlFile(jasperPrint, outputFile);
            } else {
                return "Unsupported report format: " + reportFormat;
            }


            return reportName + "." + reportFormat + " report generated at " + outputFile;
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (JRException e) {
            throw new RuntimeException(e);
        }
    }


    private String getReportPeriodDescription(String period) {
        switch (period.toLowerCase()) {
            case "last10days": return "Last 10 Days";
            case "last30days": return "Last 30 Days";
            default: return "All Employees";
        }
    }
}


