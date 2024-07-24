// routes/messageRoutes.js
import express from 'express';

import messageController from '../controllers/messageController';

const router = express.Router();

router.get('/messages/:productId', messageController.getMessagesByProductId);

export default router;
