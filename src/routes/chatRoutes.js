// routes/chatRoutes.js
import express from 'express';

import chatController from '../controllers/chatController';

const router = express.Router();

router.get('/chatRooms', chatController.getChatRooms);
router.post('/chatRooms', chatController.createChatRoom);
router.get('/myChatRooms', chatController.getMyChatRooms);

export default router;