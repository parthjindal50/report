package com.example.reportgenerator.Model;

import javax.persistence.*;

@Entity
@Table(name = "templates")
public class ReportTemplate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String source;

    private String params;

    public ReportTemplate() {
    }

    public ReportTemplate(Long id, String name, String source, String params) {
        this.id = id;
        this.name = name;
        this.source = source;
        this.params = params;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", source='" + source + '\'' +
                ", params='" + params + '\'' +
                '}';
    }
}
