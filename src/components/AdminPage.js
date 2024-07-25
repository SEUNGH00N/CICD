import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';

const AdminPage = () => {
    const [logs, setLogs] = useState('');
    const [backups, setBackups] = useState('');
    const navigate = useNavigate(); // Get the navigate function

    useEffect(() => {
        axios.get('http://localhost:8080/api/items/logs')  // 포트 8080으로 수정
            .then(response => setLogs(response.data))
            .catch(error => console.error('Error fetching logs:', error));

        axios.get('http://localhost:8080/api/items/backups')  // 포트 8080으로 수정
            .then(response => setBackups(response.data))
            .catch(error => console.error('Error fetching backups:', error));
    }, []);

    const handleNavigateToItem = () => {
        navigate('/'); // Navigate to the Item page
    };

    return (
        <div>
            <button onClick={handleNavigateToItem}>Go to Item Page</button>

            <div>
                <h1>Admin Page</h1>
                <h2>Log File Content</h2>
                <pre>{logs}</pre>
                <h2>Backup File Content</h2>
                <pre>{backups}</pre>

            </div>
        </div>
    );
};

export default AdminPage;
