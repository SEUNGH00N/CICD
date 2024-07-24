// ocrRoutes.js

import express from 'express';
import ocrController from '../controllers/ocrController';

const router = express.Router();
const { verify } = ocrController;

router.post('/verify', verify);

export default router;
