import React from "react";
import { BrowserRouter, Route, Routes, useLocation } from "react-router-dom";
import Footer from "./Components/Footer";
import Header from "./Components/Header";
import Home from "./Components/Pages/Main/Home";
import AdminSidebarContent from "./Components/Sidebar/AdminSidebarContent";
import HomeSidebarContent from "./Components/Sidebar/HomeSidebarContent";
import AdminModels from "./Components/Pages/Admin/AdminModels";
import AdminModelPage from "./Components/Pages/Admin/AdminModelPage";

function Sidebar() {
  const location = useLocation();
 
  const path = location.pathname // /admin/models/etrap
  let content: React.ReactNode;
  
  if (path.startsWith("/admin/models")) {
    content = <AdminSidebarContent />;
  } else if (path === '/') { 
    content = <HomeSidebarContent />;
  } else {
    content = <div>Содержимое по умолчанию</div>;
  }

  return (
    <aside className="w-64 bg-white border-r border-gray-200 p-4">
      {content}
    </aside>
  );
}

function App() {
  return (
    <BrowserRouter>
      <div className="flex flex-col min-h-screen">
        <Header />
        <div className="flex flex-grow">
          <Sidebar />
          <main className="flex-grow bg-gray-100 p-6">
            <Routes>
              <Route path="/admin/models" element={<AdminModels />} />
              <Route path="/admin/models/:modelName" element={<AdminModelPage />} />
              <Route path="/" element={<Home />} />
            </Routes>
          </main>
        </div>
        <Footer />
      </div>
    </BrowserRouter>
  );
}

export default App;
