import React from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import ItemPage from './components/ItemPage';
import AdminPage from './components/AdminPage';

function App() {
  return (
    <Router>
      <div className="App">
        <Routes>
          <Route path="/" element={<ItemPage />} />
          <Route path="/AdminPage" element={<AdminPage />} />


        </Routes>
      </div>
    </Router>
  );
}

export default App;