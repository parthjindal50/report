package com.example.reportgenerator.Model;

import javax.persistence.*;

@Entity
@Table(name = "reports")
public class Reports {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String source;

    private String params;

    private String reportLink;

    public Reports() {
    }

    public Reports(Long id, String source, String params) {
        this.id = id;
        this.source = source;
        this.params = params;
    }

    public Reports(Long id, String source, String params, String reportLink) {
        this.id = id;
        this.source = source;
        this.params = params;
        this.reportLink = reportLink;
    }

    public Long getId() {
        return id;
    }

    public String getSource() {
        return source;
    }

    public String getParams() {
        return params;
    }

    public String getReportLink() {
        return reportLink;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public void setParams(String params) {
        this.params = params;
    }

    public void setReportLink(String reportLink) {
        this.reportLink = reportLink;
    }
}
