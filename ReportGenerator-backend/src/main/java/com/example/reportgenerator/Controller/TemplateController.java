package com.example.reportgenerator.Controller;

import com.example.reportgenerator.Model.ReportTemplate;
import com.example.reportgenerator.Service.TemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller class responsible for handling HTTP requests related to report templates.
 */
@RestController
@RequestMapping("/template")
public class TemplateController {

    private final TemplateService templateService;

    /**
     * Constructor to inject the TemplateService dependency.
     *
     * @param templateService The service responsible for managing report templates.
     */
    @Autowired
    public TemplateController(TemplateService templateService) {
        this.templateService = templateService;
    }

    /**
     * Endpoint to retrieve all report templates.
     *
     * @return ResponseEntity containing a list of templates and a status code.
     */
    @GetMapping
    public ResponseEntity<List> getAllTemplates() {
        return templateService.getAllTemplates();
    }

    /**
     * Endpoint to retrieve a specific report template by its ID.
     *
     * @param id The ID of the template to retrieve.
     * @return ResponseEntity containing the retrieved template and a status code.
     */
    @GetMapping("/{id}")
    public ResponseEntity<ReportTemplate> getTemplate(@PathVariable Long id) {
        return templateService.getTemplate(id);
    }

    /**
     * Endpoint to create a new report template.
     *
     * @param template The template data provided in the request body.
     * @return ResponseEntity containing a response message and a status code.
     */
    @PostMapping
    public ResponseEntity<String> createTemplate(@RequestBody ReportTemplate template) {
        return templateService.createTemplate(template);
    }

    /**
     * Endpoint to update an existing report template by its ID.
     *
     * @param id The ID of the template to update.
     * @param template The updated template data provided in the request body.
     * @return ResponseEntity containing a response message and a status code.
     */
    @PutMapping("/{id}")
    public ResponseEntity<String> updateTemplate(@PathVariable Long id, @RequestBody ReportTemplate template) {
        return templateService.updateTemplate(id, template);
    }

    /**
     * Endpoint to delete a report template by its ID.
     *
     * @param id The ID of the template to delete.
     * @return ResponseEntity with a status code indicating the success of deletion.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTemplate(@PathVariable Long id) {
        return templateService.deleteTemplate(id);
    }
}
