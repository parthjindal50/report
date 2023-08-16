package com.example.reportgenerator.Repository;

import com.example.reportgenerator.Model.Reports;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReportRepository extends JpaRepository<Reports, Long> {
}
