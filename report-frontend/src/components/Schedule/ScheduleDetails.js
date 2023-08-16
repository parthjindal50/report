import React, { useState, useEffect } from 'react';
import { useParams } from 'react-router-dom';
import {useNavigate} from "react-router";

const ScheduleDetails = () => {
  const { id } = useParams();
  const [schedule, setSchedule] = useState({});
  const [templateName, setTemplateName] = useState('');
  const navigate = useNavigate();

  useEffect(() => {
    fetchScheduleDetails();
  }, [id]);

  const fetchScheduleDetails = async () => {
    try {
      const response = await fetch(`http://localhost:8080/schedule/${id}`);
      const data = await response.json();
      setSchedule(data);

      // Fetch the template name using templateId
      const templateResponse = await fetch(`http://localhost:8080/template/${data.templateId}`); 
      const templateData = await templateResponse.json();
      setTemplateName(templateData.name);
    } catch (error) {
      console.error('Error fetching schedule details:', error);
    }
  };

  const deleteSchedule = async () => {
    try {
      const response = await fetch(`http://localhost:8080/schedule/${id}`, {
        method: 'DELETE',
      });

      if (response.ok) {
        navigate('/schedules'); // Redirect to the main page
      }
    } catch (error) {
      console.error('Error deleting schedule:', error);
    }
  };

  return (
    <div>
      <h2>Schedule Details</h2>
      <p>Name: {schedule.name}</p>
      <p>Email: {schedule.email}</p>
      <p>Template Name: {templateName}</p>
      <p>Cron Expression: {schedule.cronExpression}</p>
      <button onClick={deleteSchedule}>Delete</button>
    </div>
  );
};

export default ScheduleDetails;
