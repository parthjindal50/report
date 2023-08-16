package com.example.reportgenerator.Controller;

import com.example.reportgenerator.Model.Reports;
import com.example.reportgenerator.Service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller class responsible for handling HTTP requests related to generating reports.
 */
@RestController
@RequestMapping("/report")
public class ReportController {

    private final ReportService reportService;

    /**
     * Constructor to inject the ReportService dependency.
     *
     * @param reportService The service responsible for generating reports.
     */
    @Autowired
    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    /**
     * Endpoint to generate a report based on the provided report data.
     *
     * @param reports The report data provided in the request body.
     * @return ResponseEntity containing the status code and generated report.
     */
    @PostMapping
    public ResponseEntity<String> generateReport(@RequestBody Reports reports) {
        return reportService.generateReport(reports);
    }
}
