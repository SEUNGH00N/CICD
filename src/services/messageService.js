// services/messageService.js
import messageModel from '../models/messageModel';

const messageService = {
  saveMessage: async (message) => {
    await messageModel.saveMessage(message);
  },

  getMessagesByProductId: async (productId) => {
    return await messageModel.getMessagesByProductId(productId);
  }
};

export default messageService;