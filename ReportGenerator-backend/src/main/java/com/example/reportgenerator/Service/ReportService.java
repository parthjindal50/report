package com.example.reportgenerator.Service;

import com.example.reportgenerator.Model.Reports;
import com.example.reportgenerator.Repository.ReportRepository;
import com.example.reportgenerator.Utils.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.google.gson.JsonObject;

/**
 * Service class responsible for generating reports and managing report-related operations.
 */
@Service
public class ReportService {

    private final Validator validator = new Validator();
    private final ReportRepository reportRepository;

    /**
     * Constructor to inject the ReportRepository dependency.
     *
     * @param reportRepository The repository responsible for storing report data.
     */
    @Autowired
    public ReportService(ReportRepository reportRepository) {
        this.reportRepository = reportRepository;
    }

    /**
     * Generate a report based on the provided report data.
     *
     * @param reports The report data containing parameters and details.
     * @return ResponseEntity containing a JSON response and appropriate status code.
     */
    public ResponseEntity<String> generateReport(Reports reports) {
        boolean isValid = validator.validateJson(reports.getParams());

        if (!isValid) {
            JsonObject responseJson = new JsonObject();
            responseJson.addProperty("isValid", false);
            return new ResponseEntity<>(responseJson.toString(), HttpStatus.BAD_REQUEST);
        }

        //TODO Add report generation logic
        String reportLink = "https://report-generation-sample.s3.amazonaws.com/sample-report.png";
        reports.setReportLink(reportLink);

        reportRepository.save(reports);

        JsonObject responseJson = new JsonObject();
        responseJson.addProperty("isValid", true);
        responseJson.addProperty("report", reportLink);
        return ResponseEntity.ok(responseJson.toString());
    }
}
