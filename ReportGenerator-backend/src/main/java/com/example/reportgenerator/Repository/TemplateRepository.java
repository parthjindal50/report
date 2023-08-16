package com.example.reportgenerator.Repository;

import com.example.reportgenerator.Model.ReportTemplate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TemplateRepository extends JpaRepository<ReportTemplate, Long> {
    Optional<ReportTemplate> findById(Long id);
}
