import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { useNavigate, Route, Routes } from 'react-router-dom';

function App() {
  const [items, setItems] = useState([]);
  const [name, setName] = useState('');
  const [description, setDescription] = useState('');

  const navigate = useNavigate(); // Get the navigate function

  useEffect(() => {
    axios.get('http://localhost:8080/api/items')
      .then(response => setItems(response.data))
      .catch(error => console.error('Error fetching data:', error));
  }, []);

  const handleAddItem = () => {
    axios.post('http://localhost:8080/api/items', { name, description })
      .then(response => {
        setItems([...items, response.data]);
        setName('');
        setDescription('');
      })
      .catch(error => console.error('Error adding item:', error));
  };

  const handleDeleteItem = (id) => {
    axios.delete(`/api/items/${id}`)
      .then(() => setItems(items.filter(item => item.id !== id)))
      .catch(error => console.error('Error deleting item:', error));
  };

  const handleNavigateToAdmin = () => {
    navigate('/AdminPage'); // Navigate to the Admin page
  };


  return (
    <div className="App">
      <h1>Item List</h1>
      <input
        type="text"
        value={name}
        onChange={e => setName(e.target.value)}
        placeholder="Name"
      />
      <input
        type="text"
        value={description}
        onChange={e => setDescription(e.target.value)}
        placeholder="Description"
      />
      <button onClick={handleAddItem}>Add Item</button>
      <button onClick={handleNavigateToAdmin}>Go to Admin Page</button> {/* Button for navigation */}

      <ul>
        {items.map(item => (
          <li key={item.id}>
            {item.name} - {item.description}
            <button onClick={() => handleDeleteItem(item.id)}>Delete</button>
          </li>
        ))}
      </ul>

    </div>
  );
}

export default App;
