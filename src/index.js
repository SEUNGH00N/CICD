import express from 'express';
import bodyParser from 'body-parser';
import dotenv from 'dotenv';
import userRoutes from './routes/userRoutes';
import productRoutes from './routes/productRoutes';
import chatRoutes from './routes/chatRoutes';
import messageRoutes from './routes/messageRoutes';
import messageController from './controllers/messageController';

import socketio from 'socket.io';
import cors from 'cors';
import http from 'http';

dotenv.config();

const app = express();
const port = process.env.PORT || 4000;

app.use(cors());
app.use(bodyParser.json());
app.use(bodyParser.urlencoded({ extended: true }));

app.use('/uploads', express.static('uploads'));
app.use('/uploads_id', express.static('uploads_id'));

// Routes
app.use('/users', userRoutes);
app.use('/product', productRoutes);

// 소켓 서버 설정
const socketServer = express();
const socketServerHttp = http.createServer(socketServer);
const io = socketio(socketServerHttp, {
  cors: {
    origin: '*',
  },
});
socketServer.use(cors()); // 모든 도메인에서의 요청을 허용
socketServer.use(express.json()); // JSON 요청 파싱
socketServer.use('/chats', messageRoutes);
socketServer.use('/chats', chatRoutes);

io.on('connection', (socket) => {
  console.log('Client connected');

  // 클라이언트가 메시지를 보낼 때
  socket.on('sendMessage', async (message) => {
    try {
      await messageController.saveMessage(message);
      // 모든 클라이언트에 새로운 메시지를 전송
      io.emit('newMessage', message);
    } catch (error) {
      console.error('Error sending message:', error);
      // logErrorToFile(error);

    }
  });

  // 클라이언트가 연결을 끊을 때
  socket.on('disconnect', () => {
    console.log('Client disconnected');
  });
});

// 소켓 서버 포트 설정
const SOCKET_PORT = process.env.SOCKET_PORT || 4001;

app.listen(port, () => {
  console.log(`Server is running on port ${port}`);
});

socketServerHttp.listen(SOCKET_PORT, () => {
  console.log(`Socket server is running on port ${SOCKET_PORT}`);
});