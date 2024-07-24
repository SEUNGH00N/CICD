// models/messageModel.js
import pool from '../config/db';

const messageModel = {
  saveMessage: async (message) => {
    const connection = await pool.getConnection();
    await connection.query('INSERT INTO messages (productId, text, sender, receiver, createdAt) VALUES (?, ?, ?, ?, NOW())', [message.productId, message.text, message.sender, message.receiver]);
    connection.release();
  },

  getMessagesByProductId: async (productId) => {
    const [messages] = await pool.query('SELECT * FROM messages WHERE productId = ?', [productId]);
    return messages;
  }
};

export default messageModel;