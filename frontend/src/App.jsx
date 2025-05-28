import React from 'react';
import { BrowserRouter, Route, Routes } from 'react-router-dom';
import AdminEtraps from './Components/Pages/Admin/AdminEtraps';
import Header from './Components/Header';
import Footer from './Components/Footer';
import Home from './Components/Pages/Main/Home';

function App() {
  return (
    <BrowserRouter>
      <div className="flex flex-col min-h-screen">
        <Header />
        <main className="flex-grow bg-gray-100 p-6">
          <Routes>
            <Route path="/admin/etraps" element={<AdminEtraps />} />
            <Route path="/" element={<Home />} />
            {/* другие маршруты */}
          </Routes>
        </main>
        <Footer />
      </div>
    </BrowserRouter>
  );
}

export default App;

