package com.fullstack.project.repository;

import com.fullstack.project.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * User 엔티티에 대한 데이터베이스 작업을 처리하는 JPA Repository 인터페이스입니다.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
  // 사용자 이름으로 User 객체를 조회하는 메서드 (선택적)
  User findByUsername(String username);
}
