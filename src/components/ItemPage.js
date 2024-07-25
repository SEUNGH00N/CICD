import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';

function App() {
  const [items, setItems] = useState([]);
  const [name, setName] = useState('');
  const [description, setDescription] = useState('');
  const [editItemId, setEditItemId] = useState(null); // 상태 추가
  const [editName, setEditName] = useState('');
  const [editDescription, setEditDescription] = useState('');

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
    axios.delete(`http://localhost:8080/api/items/${id}`)
      .then(() => setItems(items.filter(item => item.id !== id)))
      .catch(error => console.error('Error deleting item:', error));
  };

  const handleEditItem = (item) => {
    setEditItemId(item.id);
    setEditName(item.name);
    setEditDescription(item.description);
  };

  const handleSaveEdit = () => {
    axios.post('http://localhost:8080/api/items', {
      id: editItemId, // 기존 아이템의 ID
      name: editName,
      description: editDescription
    })
      .then(response => {
        if (editItemId) {
          // 아이템 업데이트
          setItems(items.map(item => item.id === editItemId ? response.data : item));
        } else {
          // 새 아이템 생성
          setItems([...items, response.data]);
        }
        // 상태 초기화
        setEditItemId(null);
        setEditName('');
        setEditDescription('');
      })
      .catch(error => console.error('Error saving item:', error));
  };


  const handleCancelEdit = () => {
    setEditItemId(null);
    setEditName('');
    setEditDescription('');
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
      <button onClick={handleNavigateToAdmin}>Go to Admin Page</button>

      {editItemId && (
        <div>
          <h2>Edit Item</h2>
          <input
            type="text"
            value={editName}
            onChange={e => setEditName(e.target.value)}
            placeholder="Name"
          />
          <input
            type="text"
            value={editDescription}
            onChange={e => setEditDescription(e.target.value)}
            placeholder="Description"
          />
          <button onClick={handleSaveEdit}>Save</button>
          <button onClick={handleCancelEdit}>Cancel</button>
        </div>
      )}

      <ul>
        {items.map(item => (
          <li key={item.id}>
            {item.name} - {item.description}
            <button onClick={() => handleEditItem(item)}>Edit</button>
            <button onClick={() => handleDeleteItem(item.id)}>Delete</button>
          </li>
        ))}
      </ul>

    </div>
  );
}

export default App;
