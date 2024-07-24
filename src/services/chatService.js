// services/chatService.js
import chatModel from '../models/chatModel';

const chatService = {
  getChatRooms: async (userId, productId) => {
    return await chatModel.getChatRooms(userId, productId);
  },

  createChatRoom: async (productId, userId) => {
    return await chatModel.createChatRoom(productId, userId);
  },

  getMyChatRooms: async (userId) => {
    return await chatModel.getMyChatRooms(userId);
  }
};

export default chatService;