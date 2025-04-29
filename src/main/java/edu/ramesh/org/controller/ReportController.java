package edu.ramesh.org.controller;

import edu.ramesh.org.service.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.core.io.Resource;
import org.springframework.http.ContentDisposition;

@RestController
@RequestMapping("/report")
@CrossOrigin
@RequiredArgsConstructor
public class ReportController {

    private final ReportService reportService;

    @GetMapping("/last-month")
    public ResponseEntity<Resource> downloadLastMonthReport() {
        Resource resource = reportService.generateLastMonthReport();

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"))
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        ContentDisposition.attachment()
                                .filename("last_month_report.xlsx")
                                .build()
                                .toString())
                .body(resource);
    }
}
