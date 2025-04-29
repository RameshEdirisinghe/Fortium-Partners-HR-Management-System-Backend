package edu.ramesh.org.controller;

import edu.ramesh.org.service.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/report")
@CrossOrigin
@RequiredArgsConstructor
public class ReportController {

    private final ReportService reportService;
    @GetMapping("/report")
    public String generateReport(
            @RequestParam(defaultValue = "pdf") String format,
            @RequestParam(defaultValue = "all") String period) {

            return reportService.exportReport(format, period);

    }
}
