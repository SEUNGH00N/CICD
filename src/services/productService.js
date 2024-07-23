import productModel from '../models/productModel';

const productService = {
  /**
   * 모든 상품을 가져오는 메서드
   * @param {string} searchTerm 검색어 (선택 사항)
   * @returns {Promise<Array>} 모든 상품 목록 혹은 검색된 상품 목록
   */
  getAllProducts: (searchTerm) => {
    if (searchTerm) {
      return productModel.search(searchTerm);
    } else {
      return productModel.getAll();
    }
  },

  /**
   * 특정 사용자의 상품을 가져오는 메서드
   * @param {number} userId 사용자 ID
   * @returns {Promise<Array>} 해당 사용자의 상품 목록
   */
  getUserProducts: (userId) => {
    return productModel.getByUserId(userId);
  },

  /**
   * 특정 상품 ID로 상품을 가져오는 메서드
   * @param {number} productId 상품 ID
   * @returns {Promise<Object|null>} 상품 객체 (하나) 혹은 null
   */
  getProductById: (productId) => {
    return productModel.getById(productId);
  },

  /**
   * 특정 사용자가 소유한 상품을 삭제하는 메서드
   * @param {number} productId 상품 ID
   * @param {number} userId 사용자 ID
   * @throws {Error} 권한이 없을 경우 'Unauthorized' 에러 발생
   */
  deleteProduct: (productId, userId) => {
    return productModel.getById(productId)
      .then(product => {
        if (product.user_id !== userId) {
          throw new Error('Unauthorized');
        }
        return productModel.deleteById(productId);
      });
  },

  /**
   * 사용자가 소유한 상품을 삭제하는 메서드
   * @param {number} productId 상품 ID
   * @param {number} userId 사용자 ID
   * @throws {Error} 권한이 없을 경우 'Unauthorized' 에러 발생
   * @throws {Error} 상품이 존재하지 않을 경우 'Product not found' 에러 발생
   */
  deleteUserProduct: (productId, userId) => {
    return productModel.getById(productId)
      .then(product => {
        if (!product) {
          throw new Error('Product not found');
        }
        if (product.user_id !== userId) {
          throw new Error('Unauthorized');
        }
        return productModel.deleteById(productId);
      });
  },

  /**
   * 상품을 추가하는 메서드
   * @param {number} userId 사용자 ID
   * @param {string} name 상품 이름
   * @param {string} description 상품 설명
   * @param {number} price 상품 가격
   * @param {string} imageUrl 상품 이미지 URL
   */
  addProduct: (userId, name, description, price, imageUrl) => {
    return productModel.insert(userId, name, description, price, imageUrl);
  },

  /**
   * 상품 조회수를 업데이트하는 메서드
   * @param {number} productId 상품 ID
   */
  updateProductViews: (productId) => {
    return productModel.updateViews(productId);
  },

  /**
   * 조회수 순으로 상품을 가져오는 메서드
   * @returns {Promise<Array>} 조회수 순으로 정렬된 상품 목록
   */
  getProductsByViews: () => {
    return productModel.getByViews();
  },

  /**
   * 특정 상품의 상태를 '판매 완료'로 변경하는 메서드
   * @param {number} productId 상품 ID
   * @returns {Promise<Object>} 업데이트 결과
   */
  updateStatusToSold: (productId) => {
    return productModel.updateStatusToSold(productId);
  },

  /**
   * 상품 정보를 업데이트하는 메서드 (이미지 포함)
   * @param {number} productId 상품 ID
   * @param {number} userId 사용자 ID
   * @param {string} name 상품 이름
   * @param {string} description 상품 설명
   * @param {number} price 상품 가격
   * @param {string} imageUrl 상품 이미지 URL
   * @returns {Promise<Object>} 업데이트 결과
   */
  updateProduct: (productId, userId, name, description, price, imageUrl) => {
    return productModel.updateProduct(productId, userId, name, description, price, imageUrl);
  },

  /**
   * 특정 사용자가 특정 상품을 찜했는지 여부를 확인하는 메서드
   * @param {number} userId 사용자 ID
   * @param {number} productId 상품 ID
   * @returns {Promise<boolean>} 찜 여부
   */
  checkFavorite: (userId, productId) => {
    return productModel.checkFavorite(userId, productId);
  },

  /**
   * 특정 사용자가 특정 상품의 찜 상태를 토글하는 메서드
   * @param {number} userId 사용자 ID
   * @param {number} productId 상품 ID
   * @returns {Promise<boolean>} 토글된 찜 상태
   */
  toggleFavorite: (userId, productId) => {
    return productModel.toggleFavorite(userId, productId);
  },

  /**
   * 특정 상품의 찜 개수를 가져오는 메서드
   * @param {number} productId 상품 ID
   * @returns {Promise<number>} 찜 개수
   */
  getFavoriteCount: (productId) => {
    return productModel.getFavoriteCount(productId);
  },

  /**
   * 특정 상품의 찜 개수를 업데이트하는 메서드
   * @param {number} productId 상품 ID
   * @param {number} favoritesCount 찜 개수
   */
  updateFavoritesCount: (productId, favoritesCount) => {
    return productModel.updateFavoritesCount(productId, favoritesCount);
  },

  /**
   * 특정 사용자의 찜 목록을 가져오는 메서드
   * @param {number} userId 사용자 ID
   * @returns {Promise<Array>} 찜 목록
   */
  getFavoritesByUserId: (userId) => {
    return productModel.getFavoritesByUserId(userId);
  },

  /**
   * 특정 상품의 판매자 ID를 가져오는 메서드
   * @param {number} productId 상품 ID
   * @returns {Promise<number>} 판매자 ID
   */
  getSellerId: (productId) => {
    return productModel.getSellerId(productId);
  },

  /**
   * 특정 상품에 대한 평점을 추가하는 메서드
   * @param {number} userId 사용자 ID
   * @param {number} productId 상품 ID
   * @param {number} rating 평점
   */
  addRating: (userId, productId, rating) => {
    return productModel.addRating(userId, productId, rating);
  },

  /**
   * 특정 사용자가 특정 상품을 찜했는지 여부를 확인하는 메서드
   * @param {number} userId 사용자 ID
   * @param {number} productId 상품 ID
   * @returns {Promise<boolean>} 찜 여부
   */
  isFavorite: (userId, productId) => {
    return productModel.isFavorite(userId, productId);
  },

  /**
   * 추가 상품 목록을 가져오는 메서드
   * @param {number} currentProductId 현재 상품 ID
   * @returns {Promise<Array>} 추가 상품 목록
   */
  getMoreProducts: (currentProductId) => {
    return productModel.getMoreProducts(currentProductId);
  },

  /**
   * 특정 상품의 상세 정보를 가져오는 메서드
   * @param {number} productId 상품 ID
   * @returns {Promise<Object|null>} 상품 정보 혹은 null
   */
  getProductById: (productId) => {
    return productModel.getProductById(productId);
  },

  /**
   * 인기 검색어 목록을 가져오는 메서드
   * @returns {Promise<Array>} 인기 검색어 목록
   */
  getTopSearchKeywords: () => {
    return productModel.getTopSearchKeywords();
  },

  /**
   * 상품에 대한 신고를 추가하는 메서드
   * @param {number} productId 상품 ID
   * @param {number} userId 사용자 ID
   * @param {number} sellerId 판매자 ID
   * @param {string} reason 신고 사유
   * @param {string} details 신고 상세 정보
   * @returns {Promise<Object>} 신고 결과
   */
  addReport: (productId, userId, sellerId, reason, details) => {
    return productModel.addReport(productId, userId, sellerId, reason, details);
  },

  /**
   * 모든 신고 목록을 가져오는 메서드
   * @returns {Promise<Array>} 모든 신고 목록
   */
  getAllReports: () => {
    return productModel.getAllReports();
  },
};

export default productService;