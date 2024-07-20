// const request = require('supertest');
// const express = require('express');
// const multer = require('multer');
// const path = require('path');
// const fs = require('fs');
// const app = express();
// const router = require('../routes/productRoutes'); // 실제 경로에 맞게 수정

// // Express 앱 설정
// app.use(express.json());
// app.use('/api/products', router);

// // 테스트 데이터 파일 경로
// const testImagePath = path.join(__dirname, 'uploads', 'testfile.jpg');


// // 테스트 케이스
// describe('Product Routes', () => {
//   beforeAll(async () => {
//     // 서버 설정 및 테스트 DB 연결 설정 등
//     // 데이터베이스 초기화 또는 테스트 데이터 삽입
//   });

//   afterAll(async () => {
//     // 데이터베이스 연결 해제 및 기타 정리 작업
//     // 테스트 데이터 삭제 또는 데이터베이스 정리
//   });

//   test('GET /api/products/latest - Should return latest products', async () => {
//     const response = await request(app).get('/api/products/latest');
//     expect(response.status).toBe(200);
//     expect(response.body).toBeDefined(); // 실제 반환되는 데이터 구조에 맞게 검증
//   });

//   test('GET /api/products/detail/:productId - Should return specific product details', async () => {
//     const productId = 150; // 테스트할 상품 ID, 데이터베이스에 존재하는 ID를 사용해야 합니다
//     const response = await request(app).get(`/api/products/detail/${productId}`);
//     expect(response.status).toBe(200);
//     expect(response.body).toHaveProperty('id', productId); // 반환된 객체에 특정 속성 확인
//   });

//   // test('POST /api/products/addProduct - Should add a new product', async () => {
//   //   const newProduct = {
//   //     name: 'Test Product',
//   //     description: 'Test Description',
//   //     price: 100,
//   //   };

//   //   const response = await request(app)
//   //     .post('/api/products/addProduct')
//   //     .field('name', newProduct.name)
//   //     .field('description', newProduct.description)
//   //     .field('price', newProduct.price)
//   //     .attach('image', testImagePath); // 테스트 이미지 파일 경로

//   //   expect(response.status).toBe(201); // 상품 추가 성공시 201 상태 코드
//   //   expect(response.body).toHaveProperty('id'); // 반환된 객체에 ID가 있는지 확인
//   // });

//   // test('DELETE /api/products/:productId - Should delete a product by ID', async () => {
//   //   const productId = 150; // 삭제할 상품 ID, 데이터베이스에 존재하는 ID를 사용해야 합니다
//   //   const response = await request(app).delete(`/api/products/${productId}`);
//   //   expect(response.status).toBe(200);
//   //   expect(response.body).toHaveProperty('message', 'Product deleted'); // 응답 메시지 확인
//   // });

//   // 다른 테스트 케이스 추가
// });
