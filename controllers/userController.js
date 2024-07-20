const userService = require('../services/userService');

/**
 * 사용자 컨트롤러 객체
 */
const userController = {
  /**
   * 사용자 등록 컨트롤러
   * @param {object} req - 요청 객체
   * @param {object} res - 응답 객체
   */
  signup: (req, res) => {
    const { id, password, confirmPassword, email, department, grade, name } = req.body;
    const studentIdImageUrl = req.file ? req.file.filename : null;

    userService.signupUser(id, password, confirmPassword, email, department, grade, name, studentIdImageUrl)
      .then(() => res.status(201).json({ message: '사용자가 성공적으로 등록되었습니다.' }))
      .catch(error => {
        console.error('회원 가입 중 오류 발생:', error);
        res.status(500).json({ message: error.message });
      });
  },

  /**
   * 사용자 로그인 컨트롤러
   * @param {object} req - 요청 객체
   * @param {object} res - 응답 객체
   */
  login: (req, res) => {
    const { id, password } = req.body;

    userService.loginUser(id, password)
      .then(userInfo => res.status(200).json(userInfo))
      .catch(error => {
        console.error('로그인 중 오류 발생:', error);
        res.status(error.statusCode || 500).json({ message: error.message });
      });
  },

  /**
   * 사용자 ID 중복 확인 컨트롤러
   * @param {object} req - 요청 객체
   * @param {object} res - 응답 객체
   */
  checkUser: (req, res) => {
    const { id } = req.query;

    userService.checkUserAvailability(id)
      .then(available => res.status(200).json({ available }))
      .catch(error => {
        console.error('사용자 ID 중복 확인 중 오류 발생:', error);
        res.status(500).json({ message: '서버 내부 오류' });
      });
  },

  /**
   * 모든 승인되지 않은 사용자 정보 가져오기 컨트롤러
   * @param {object} req - 요청 객체
   * @param {object} res - 응답 객체
   */
  getPendingUsers: (req, res) => {
    userService.getPendingUsers()
      .then(pendingUsers => res.status(200).json(pendingUsers))
      .catch(error => {
        console.error('승인 대기 중인 사용자 정보 조회 중 오류 발생:', error);
        res.status(500).json({ message: '서버 내부 오류' });
      });
  },

  /**
   * 사용자의 승인 상태 업데이트 컨트롤러
   * @param {object} req - 요청 객체
   * @param {object} res - 응답 객체
   */
  updateApprovalStatus: (req, res) => {
    const { userId } = req.params;
    const { approvalStatus, rejectionReason } = req.body;

    userService.updateApprovalStatus(userId, approvalStatus, rejectionReason)
      .then(() => res.status(200).json({ message: '사용자 승인 상태가 성공적으로 업데이트되었습니다.' }))
      .catch(error => {
        console.error('사용자 승인 상태 업데이트 중 오류 발생:', error);
        res.status(500).json({ message: '서버 내부 오류' });
      });
  },

  /**
   * 승인된 사용자 정보 가져오기 컨트롤러
   * @param {object} req - 요청 객체
   * @param {object} res - 응답 객체
   */
  getApprovedUsers: (req, res) => {
    userService.getApprovedUsers()
      .then(approvedUsers => res.status(200).json(approvedUsers))
      .catch(error => {
        console.error('승인된 사용자 정보 조회 중 오류 발생:', error);
        res.status(500).json({ message: '서버 내부 오류' });
      });
  },

  /**
   * 사용자 유형 정보 가져오기 컨트롤러
   * @param {object} req - 요청 객체
   * @param {object} res - 응답 객체
   */
  getUserType: (req, res) => {
    const { userId } = req.query;

    userService.getUserTypeById(userId)
      .then(userType => res.status(200).json({ userType }))
      .catch(error => {
        console.error('사용자 유형 조회 중 오류 발생:', error);
        res.status(500).json({ message: '서버 내부 오류' });
      });
  },

  /**
   * 내 정보 조회 컨트롤러
   * @param {object} req - 요청 객체
   * @param {object} res - 응답 객체
   */
  getMyInfo: (req, res) => {
    const { userId, password } = req.body;

    userService.getUserInfo(userId, password)
      .then(userInfo => res.status(200).json(userInfo))
      .catch(error => {
        console.error('내 정보 조회 중 오류 발생:', error);
        res.status(error.statusCode || 500).json({ message: error.message });
      });
  },

  /**
   * 비밀번호 변경 컨트롤러
   * @param {object} req - 요청 객체
   * @param {object} res - 응답 객체
   */
  changePassword: (req, res) => {
    const { userId, currentPassword, newPassword } = req.body;

    userService.changePassword(userId, currentPassword, newPassword)
      .then(() => res.status(200).json({ message: '비밀번호가 성공적으로 변경되었습니다.' }))
      .catch(error => {
        console.error('비밀번호 변경 중 오류 발생:', error);
        res.status(error.statusCode || 500).json({ message: error.message });
      });
  },

  /**
   * 사용자 정보 수정 컨트롤러
   * @param {object} req - 요청 객체
   * @param {object} res - 응답 객체
   */
  editUserInfo: (req, res) => {
    const { userId, editedUserInfo } = req.body;

    userService.editUserInfo(userId, editedUserInfo)
      .then(() => res.status(200).json({ message: '사용자 정보가 성공적으로 수정되었습니다.' }))
      .catch(error => {
        console.error('사용자 정보 수정 중 오류 발생:', error);
        res.status(error.statusCode || 500).json({ message: error.message });
      });
  },

  /**
   * 세션에 저장된 사용자 ID를 기반으로 사용자 정보를 반환하는 엔드포인트
   * @param {object} req - 요청 객체
   * @param {object} res - 응답 객체
   */
  getUserInfo: (req, res) => {
    const userId = req.headers.user_id; // 사용자 ID는 요청 헤더에서 가져옵니다.

    if (!userId) {
      return res.status(401).send('Unauthorized');
    }

    userService.getUserInfoWithSalesAndBalance(userId)
      .then(userInfoWithSalesAndBalance => res.json(userInfoWithSalesAndBalance))
      .catch(error => {
        console.error('사용자 정보 조회 중 오류 발생:', error);
        res.status(500).json({ error: '사용자 정보 조회 중 오류 발생' });
      });
  },

  /**
   * 상품의 판매자 정보를 가져오는 컨트롤러
   * @param {object} req - 요청 객체
   * @param {object} res - 응답 객체
   */
  getSellerInfo: (req, res) => {
    const { productId } = req.params;

    userService.getSellerInfo(productId)
      .then(sellerInfo => {
        if (!sellerInfo) {
          return res.status(404).json({ error: '상품을 찾을 수 없습니다.' });
        }
        res.status(200).json(sellerInfo);
      })
      .catch(error => {
        console.error('상품 정보 조회 오류:', error);
        res.status(500).json({ error: '서버 오류: 상품 정보를 가져올 수 없습니다.' });
      });
  }
};

module.exports = userController;