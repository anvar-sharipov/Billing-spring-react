import React from 'react';
import { BrowserRouter, Route, Routes } from 'react-router-dom';
import AdminEtraps from './Components/AdminEtraps';
import './index.css';

function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/admin/etraps" element={<AdminEtraps />} />
        {/* другие маршруты */}
      </Routes>
    </BrowserRouter>
  );
}

export default App;
