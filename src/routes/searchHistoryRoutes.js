import express from 'express';

import searchHistoryController from '../controllers/searchHistoryController';

const router = express.Router();

router.post('/', searchHistoryController.saveSearchTerm);
router.get('/', searchHistoryController.getLatestSearchTerm);
router.get('/:userId', searchHistoryController.getSearchKeywordsByUserId);
router.delete('/delete/:id', searchHistoryController.deleteSearchKeywordById);

export default router;