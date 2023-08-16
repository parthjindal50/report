package com.example.reportgenerator.Service;

import com.example.reportgenerator.Model.ReportTemplate;
import com.example.reportgenerator.Repository.TemplateRepository;
import com.example.reportgenerator.Utils.Validator;
import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service class responsible for managing report template-related operations.
 */
@Service
public class TemplateService {

    private final Validator validator = new Validator();

    private final TemplateRepository templateRepository;

    /**
     * Constructor to inject the TemplateRepository dependency.
     *
     * @param templateRepository The repository responsible for storing report templates.
     */
    @Autowired
    public TemplateService(TemplateRepository templateRepository) {
        this.templateRepository = templateRepository;
    }

    /**
     * Retrieve all report templates.
     *
     * @return ResponseEntity containing a list of templates and a status code.
     */
    public ResponseEntity<List> getAllTemplates() {
        List<ReportTemplate> templates = templateRepository.findAll();
        return ResponseEntity.ok(templates);
    }

    /**
     * Retrieve a specific report template by its ID.
     *
     * @param id The ID of the template to retrieve.
     * @return ResponseEntity containing the retrieved template or a not found response.
     */
    public ResponseEntity<ReportTemplate> getTemplate(Long id) {
        Optional<ReportTemplate> template = templateRepository.findById(id);
        return template.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Create a new report template.
     *
     * @param template The template to be created.
     * @return ResponseEntity containing a response message and a status code.
     */
    public ResponseEntity<String> createTemplate(ReportTemplate template) {
        boolean isValid = validator.validateJson(template.getParams());

        if (!isValid) {
            JsonObject responseJson = new JsonObject();
            responseJson.addProperty("isValid", false);
            return new ResponseEntity<>(responseJson.toString(), HttpStatus.BAD_REQUEST);
        }

        templateRepository.save(template);

        JsonObject responseJson = new JsonObject();
        responseJson.addProperty("isValid", true);
        responseJson.addProperty("template", template.toString());
        return ResponseEntity.ok(responseJson.toString());
    }

    /**
     * Update an existing report template.
     *
     * @param id The ID of the template to be updated.
     * @param updatedTemplate The updated template data.
     * @return ResponseEntity containing a response message and a status code.
     */
    public ResponseEntity<String> updateTemplate(Long id, ReportTemplate updatedTemplate) {
        Optional<ReportTemplate> existingTemplate = templateRepository.findById(id);

        if (existingTemplate.isPresent()) {
            boolean isValid = validator.validateJson(updatedTemplate.getParams());

            if (!isValid) {
                JsonObject responseJson = new JsonObject();
                responseJson.addProperty("isValid", false);
                return new ResponseEntity<>(responseJson.toString(), HttpStatus.BAD_REQUEST);
            }

            ReportTemplate template = existingTemplate.get();
            template.setName(updatedTemplate.getName());
            template.setParams(updatedTemplate.getParams());
            template.setSource(updatedTemplate.getSource());

            templateRepository.save(template);

            JsonObject responseJson = new JsonObject();
            responseJson.addProperty("isValid", true);
            responseJson.addProperty("template", template.toString());
            return ResponseEntity.ok(responseJson.toString());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Delete a report template by its ID.
     *
     * @param id The ID of the template to be deleted.
     * @return ResponseEntity indicating the success of deletion or a not found response.
     */
    public ResponseEntity<Void> deleteTemplate(Long id) {
        Optional<ReportTemplate> template = templateRepository.findById(id);

        if (template.isPresent()) {
            templateRepository.delete(template.get());
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
