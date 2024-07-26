package com.fullstack.project.service;

import com.fullstack.project.model.User;
import com.fullstack.project.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * User 엔티티에 대한 비즈니스 로직을 처리하는 서비스 클래스입니다.
 */
@Service
public class UserService {

  @Autowired
  private UserRepository userRepository;

  /**
   * 데이터베이스에서 모든 User 객체를 조회하는 메서드입니다.
   * 
   * @return 모든 User 객체의 리스트.
   */
  public List<User> getAllUsers() {
    return userRepository.findAll();
  }

  /**
   * 주어진 ID를 가진 User 객체를 조회하는 메서드입니다.
   * 
   * @param id 조회할 User 객체의 ID.
   * @return 요청한 ID의 User 객체, 존재하지 않을 경우 null.
   */
  public User getUserById(Long id) {
    return userRepository.findById(id).orElse(null);
  }

  /**
   * User 객체를 저장하거나 업데이트하는 메서드입니다.
   * 
   * @param user 저장할 User 객체.
   * @return 저장된 User 객체.
   */
  @Transactional
  public User saveUser(User user) {
    return userRepository.save(user);
  }

  /**
   * 주어진 ID를 가진 User 객체를 삭제하는 메서드입니다.
   * 
   * @param id 삭제할 User 객체의 ID.
   */
  @Transactional
  public void deleteUser(Long id) {
    userRepository.deleteById(id);
  }
}
