package com.fullstack.project.controller;

import com.fullstack.project.model.User;
import com.fullstack.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * RESTful API를 제공하는 UserController 클래스입니다.
 * <p>
 * 이 컨트롤러는 User 엔티티에 대한 CRUD 작업을 처리합니다.
 * </p>
 */
@RestController
@RequestMapping("/api/users")
public class UserController {

  @Autowired
  private UserService userService;

  /**
   * 모든 User 객체를 가져오는 메서드입니다.
   * 
   * @return 모든 User 객체의 리스트를 포함하는 ResponseEntity 객체.
   */
  @GetMapping
  public List<User> getAllUsers() {
    return userService.getAllUsers();
  }

  /**
   * 특정 ID를 가진 User 객체를 가져오는 메서드입니다.
   * 
   * @param id 검색할 User 객체의 ID.
   * @return 요청한 ID의 User 객체와 HTTP 상태 코드 200 OK를 포함하는 ResponseEntity 객체,
   *         또는 User 객체가 존재하지 않을 경우 HTTP 상태 코드 404 Not Found를 포함하는 ResponseEntity
   *         객체.
   */
  @GetMapping("/{id}")
  public ResponseEntity<User> getUserById(@PathVariable Long id) {
    User user = userService.getUserById(id);
    return user != null ? new ResponseEntity<>(user, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
  }

  /**
   * 새 User 객체를 생성하는 메서드입니다.
   * 
   * TODO: access.log 파일에 로그 저장
   * 
   * @param user 요청 본문에서 전달된 User 객체.
   * @return 생성된 User 객체와 HTTP 상태 코드 201 Created를 포함하는 ResponseEntity 객체.
   */
  @PostMapping
  public ResponseEntity<User> createUser(@RequestBody User user) {
    return new ResponseEntity<>(userService.saveUser(user), HttpStatus.CREATED);
  }

  /**
   * 특정 ID를 가진 User 객체를 업데이트하는 메서드입니다.
   * 
   * @param id   업데이트할 User 객체의 ID.
   * @param user 요청 본문에서 전달된 User 객체 (업데이트할 데이터).
   * @return 업데이트된 User 객체와 HTTP 상태 코드 200 OK를 포함하는 ResponseEntity 객체,
   *         또는 User 객체가 존재하지 않을 경우 HTTP 상태 코드 404 Not Found를 포함하는 ResponseEntity
   *         객체.
   */
  @PutMapping("/{id}")
  public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User user) {
    User existingUser = userService.getUserById(id);
    if (existingUser != null) {
      user.setId(id);
      return new ResponseEntity<>(userService.saveUser(user), HttpStatus.OK);
    }
    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
  }

  /**
   * 특정 ID를 가진 User 객체를 삭제하는 메서드입니다.
   * 
   * TODO: access.log 파일에 로그 저장
   * 
   * @param id 삭제할 User 객체의 ID.
   * @return HTTP 상태 코드 204 No Content를 포함하는 ResponseEntity 객체,
   *         또는 User 객체가 존재하지 않을 경우 HTTP 상태 코드 404 Not Found를 포함하는 ResponseEntity
   *         객체.
   */
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
    User user = userService.getUserById(id);
    if (user != null) {
      userService.deleteUser(id);
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
  }
}
