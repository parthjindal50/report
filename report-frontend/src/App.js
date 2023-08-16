import React, { useState } from 'react';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import {useNavigate} from "react-router";
import './App.css';
import Navbar from './components/Navbar';
import GenerateReport from './components/GenerateReport/ReportGenerator';
import ReportTemplate from './components/Template/ReportTemplate';
import TemplateCreate from './components/Template/TemplateCreate';
import TemplateDetails from './components/Template/TemplateDetails';
import ScheduleReport from './components/Schedule/ScheduleReport';
import ScheduleCreate from './components/Schedule/ScheduleCreate';
import ScheduleDetails from './components/Schedule/ScheduleDetails';

function App() {
  return (
    <Router>
      <div className="App">
        <Navbar />
        <Routes>
          <Route path="/generate-report" element={<GenerateReport />} />
          <Route path="/templates/create" element={<TemplateCreate />} />
          <Route path="/templates/:id" element={<TemplateDetails />} />
          <Route path="/templates" element={<ReportTemplate />} />
          <Route path="/schedules/create" element={<ScheduleCreate />} />
          <Route path="/schedules/:id" element={<ScheduleDetails />} />
          <Route path="/schedules" element={<ScheduleReport />} />
        </Routes>
      </div>
    </Router>
  );
}

export default App;
