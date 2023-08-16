import React, { useState, useEffect } from 'react';
import {useNavigate} from "react-router";

const ScheduleCreate = () => {
  const [name, setName] = useState('');
  const [email, setEmail] = useState('');
  const [templateId, setTemplateId] = useState('');
  const [time, setTime] = useState('12:00');
  const [cronExpression, setCronExpression] = useState('');
  const [templates, setTemplates] = useState([]);
  const navigate = useNavigate();

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

  const generateCronExpression = () => {
    const [hours, minutes] = time.split(':');
    // Generate cron expression in the format required by Spring @Scheduled annotation
    const cron = `0 ${minutes} ${hours} * * *`;
    setCronExpression(cron);
  };

  const createSchedule = async () => {
    try {
      const requestData = {
        name,
        email,
        templateId,
        cronExpression,
      };

      const response = await fetch('http://localhost:8080/schedule', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(requestData),
      });

      if (response.ok) {
        navigate('/schedules'); // Redirect to the main page
      }
    } catch (error) {
      console.error('Error creating schedule:', error);
    }
  };

  return (
    <div>
      <h2>Create Schedule</h2>
      <form>
        <label>
          Name:
          <input type="text" value={name} onChange={(e) => setName(e.target.value)} />
        </label>
        <br />
        <label>
          Email:
          <input type="text" value={email} onChange={(e) => setEmail(e.target.value)} />
        </label>
        <br />
        <label>
          Template:
          <select value={templateId} onChange={(e) => setTemplateId(e.target.value)}>
            <option value="">Select a template</option>
            {templates.map((template) => (
              <option key={template.id} value={template.id}>
                {template.name}
              </option>
            ))}
          </select>
        </label>
        <br />
        <label>
            Time:
            <input type="time" value={time} onChange={(e) => setTime(e.target.value)} />
        </label>
        <button type="button" onClick={generateCronExpression}>
            Generate Cron Expression
        </button>
        <br />
        <label>
            Cron Expression:
            <span>{cronExpression}</span>
        </label>
        <br />
        <button type="button" onClick={createSchedule}>
          Create
        </button>
      </form>
    </div>
  );
};

export default ScheduleCreate;
