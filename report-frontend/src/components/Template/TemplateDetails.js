import React, { useState, useEffect } from 'react';
import { useParams } from 'react-router-dom';
import {useNavigate} from "react-router";

const TemplateDetails = () => {
  const { id } = useParams();
  const [template, setTemplate] = useState({});
  const [editable, setEditable] = useState(false);
  const [source, setSource] = useState('');
  const [params, setParams] = useState('{}');
  const [isValidJson, setIsValidJson] = useState(true);
  const navigate = useNavigate();

  useEffect(() => {
    fetchTemplateDetails();
  }, [id]);

  const fetchTemplateDetails = async () => {
    try {
      const response = await fetch(`http://localhost:8080/template/${id}`);
      const data = await response.json();
      setTemplate(data);
      setSource(data.source);
      setParams(data.params);
    } catch (error) {
      console.error('Error fetching template details:', error);
    }
  };

  const editTemplate = async () => {
    try {
      const requestData = {
        id: template.id,
        name: template.name,
        source,
        params,
      };

      const response = await fetch(`http://localhost:8080/template/${id}`, {
        method: 'PUT',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(requestData),
      });

      const data = await response.json();

      if (data.isValid) {
        setIsValidJson(true);
        setEditable(false);
        fetchTemplateDetails(); // Refresh the details after editing
      } else {
        setIsValidJson(false);
      }
    } catch (error) {
      console.error('Error editing template:', error);
    }
  };

  const deleteTemplate = async () => {
    try {
      const response = await fetch(`http://localhost:8080/template/${id}`, {
        method: 'DELETE',
      });

      if (response.ok) {
        navigate('/templates');
      }
    } catch (error) {
      console.error('Error deleting template:', error);
    }
  };

  return (
    <div>
      <h2>Template Details</h2>
      <p>Name: {template.name}</p>
      <p>Source: {template.source}</p>
      <p>Params: {JSON.stringify(JSON.parse(template.params || '{}'), null, 2)}</p>
      {editable ? (
        <div>
          <label>
            Source:
            <input type="text" value={source} onChange={(e) => setSource(e.target.value)} />
          </label>
          <br />
          <label>
            Params (JSON):
            <textarea value={params} onChange={(e) => setParams(e.target.value)} />
          </label>
          <br />
          <button onClick={editTemplate}>Save</button>
          <button onClick={() => setEditable(false)}>Cancel</button>
          {!isValidJson && <p>Invalid JSON</p>}
        </div>
      ) : (
        <div>
          <button onClick={() => setEditable(true)}>Edit</button>
          <button onClick={deleteTemplate}>Delete</button>
        </div>
      )}
    </div>
  );
};

export default TemplateDetails;
