import React, { useState, useEffect } from 'react';
import { Link } from 'react-router-dom';

const ScheduleReport = () => {
  const [schedules, setSchedules] = useState([]);

  useEffect(() => {
    fetchSchedules();
  }, []);

  const fetchSchedules = async () => {
    try {
      const response = await fetch('http://localhost:8080/schedule');
      const data = await response.json();
      setSchedules(data);
    } catch (error) {
      console.error('Error fetching schedules:', error);
    }
  };

  return (
    <div>
      <h2>Schedule Reports</h2>
      {schedules.map((schedule) => (
        <div key={schedule.id}>
          <Link to={`/schedules/${schedule.id}`}>{schedule.name}</Link>
        </div>
      ))}
      <Link to="/schedules/create">Create New Schedule</Link>
    </div>
  );
};

export default ScheduleReport;
