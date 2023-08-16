import React, { useState } from 'react';
import {useNavigate} from "react-router";

const TemplateCreate = () => {
  const [name, setName] = useState('');
  const [source, setSource] = useState('');
  const [params, setParams] = useState('');
  const [isValidJson, setIsValidJson] = useState(true);
  const navigate = useNavigate();

  const createTemplate = async () => {
    try {
      const requestData = {
        name,
        source,
        params,
      };

      const response = await fetch('http://localhost:8080/template', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(requestData),
      });

      const data = await response.json();

      if (data.isValid) {
        setIsValidJson(true);
        navigate('/templates'); // Redirect to the main page
      } else {
        setIsValidJson(false);
      }
    } catch (error) {
      console.error('Error creating template:', error);
    }
  };

  return (
    <div>
      <h2>Create Template</h2>
      <form>
      <label>
          Name:
          <input type="text" value={name} onChange={(e) => setName(e.target.value)} />
        </label>
        <br />
        <label>
          Source:
          <input type="text" value={source} onChange={(e) => setSource(e.target.value)} />
        </label>
        <br />
        <label>
          Params:
          <textarea value={params} onChange={(e) => setParams(e.target.value)} />
        </label>
        <button type="button" onClick={createTemplate}>
          Create
        </button>
      </form>
      {!isValidJson && <p>Invalid JSON</p>}
    </div>
  );
};

export default TemplateCreate;
