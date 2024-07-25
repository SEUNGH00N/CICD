import React, { useState, useEffect } from 'react';
import axios from 'axios';

const AdminPage = () => {
    const [logs, setLogs] = useState('');
    const [backups, setBackups] = useState('');

    useEffect(() => {
        axios.get('http://localhost:8080/api/items/logs')  // 포트 8080으로 수정
            .then(response => setLogs(response.data))
            .catch(error => console.error('Error fetching logs:', error));

        axios.get('http://localhost:8080/api/items/backups')  // 포트 8080으로 수정
            .then(response => setBackups(response.data))
            .catch(error => console.error('Error fetching backups:', error));
    }, []);

    return (
        <div>
            <h1>Admin Page</h1>
            <h2>Log File Content</h2>
            <pre>{logs}</pre>
            <h2>Backup File Content</h2>
            <pre>{backups}</pre>
        </div>
    );
};

export default AdminPage;
