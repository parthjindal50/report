import React, { useState, useEffect } from 'react';
import { Link } from 'react-router-dom';

const ReportTemplate = () => {
  const [templates, setTemplates] = useState([]);

  useEffect(() => {
    fetchTemplates();
  }, []);

  const fetchTemplates = async () => {
    try {
      const response = await fetch('http://localhost:8080/template');
      const data = await response.json();
      setTemplates(data);
    } catch (error) {
      console.error('Error fetching templates:', error);
    }
  };

  return (
    <div>
      <h2>Report Templates</h2>
      {templates.map((template) => (
        <div key={template.id}>
          <Link to={`/templates/${template.id}`}>{template.name}</Link>
        </div>
      ))}
      <Link to="/templates/create">Create New Template</Link>
    </div>
  );
};

export default ReportTemplate;
