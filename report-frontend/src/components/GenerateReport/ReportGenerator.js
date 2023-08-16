import React, { useState } from 'react';

const GenerateReport = ({ apiEndpoint }) => {
  const [source, setSource] = useState('');
  const [params, setParams] = useState('');
  const [report, setReport] = useState('');
  const [isValidJson, setIsValidJson] = useState(true);

  const generateReport = async () => {
    try {
      const requestData = {
        source,
        params,
      };

      console.log(requestData)

      const response = await fetch('http://localhost:8080/report', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(requestData),
      });

      const data = await response.json();

      if (data.isValid) {
        setIsValidJson(true);
        setReport(data.report);
      } else {
        setIsValidJson(false);
      }
    } catch (error) {
      console.error('Error generating report:', error);
    }
  };

  return (
    <div>
      <h2>Generate Report</h2>
      <form>
        <label>
          Source:
          <input type="text" value={source} onChange={(e) => setSource(e.target.value)} />
        </label>
        <br />
        <label>
          Params:
          <textarea value={params} onChange={(e) => setParams(e.target.value)} />
        </label>
        <br />
        <button type="button" onClick={generateReport}>
          Generate
        </button>
      </form>
      {!isValidJson && <p>Invalid JSON</p>}
      {report && (
        <p>
          Report: <a href={report} target="_blank" rel="noopener noreferrer">{report}</a>
        </p>
      )}
    </div>
  );
};

export default GenerateReport;
