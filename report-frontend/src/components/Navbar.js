import React from 'react';
import { AppBar, Toolbar, Button } from '@mui/material';
import { Link as RouterLink } from 'react-router-dom';

const Navbar = () => {
  

  return (
    <AppBar position="static">
      <Toolbar sx={{ justifyContent: 'space-between' }}>
        <Button component={RouterLink} to="/generate-report" color="inherit">Generate Report</Button>
        <Button component={RouterLink} to="/templates" color="inherit">Report Template</Button>
        <Button component={RouterLink} to="/schedules" color="inherit">Schedule Report</Button>
      </Toolbar>
    </AppBar>
  );
};

export default Navbar;