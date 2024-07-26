package com.fullstack.project.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

/**
 * 데이터베이스의 User 테이블과 매핑되는 JPA 엔티티 클래스입니다.
 * <p>
 * 이 클래스는 User 객체를 데이터베이스에 저장하고 조회하기 위해 사용됩니다.
 * </p>
 */
@Entity
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id; // User 객체의 고유 식별자

  private String username; // 사용자의 사용자 이름
  private String email; // 사용자의 이메일 주소
  private String password; // 사용자의 비밀번호

  // 기본 생성자
  public User() {
  }

  // 모든 필드를 초기화하는 생성자
  public User(String username, String email, String password) {
    this.username = username;
    this.email = email;
    this.password = password;
  }

  // Getter 및 Setter 메서드

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  @Override
  public String toString() {
    return "User{" +
        "id=" + id +
        ", username='" + username + '\'' +
        ", email='" + email + '\'' +
        ", password='" + password + '\'' +
        '}';
  }
}
