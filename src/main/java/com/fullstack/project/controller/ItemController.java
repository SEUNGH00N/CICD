package com.fullstack.project.controller;

import com.fullstack.project.model.Item;
import com.fullstack.project.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

/**
 * RESTful API를 제공하는 ItemController 클래스입니다.
 * <p>
 * 이 컨트롤러는 Item 엔티티에 대한 CRUD 작업을 처리합니다.
 * </p>
 */
@RestController
@RequestMapping("/api/items")
public class ItemController {

  @Autowired
  private ItemService itemService; // ItemService를 주입 받아 사용합니다.

  /**
   * 모든 Item 객체를 가져오는 메서드입니다.
   * 
   * @return 모든 Item 객체의 리스트를 포함하는 ResponseEntity 객체.
   */
  @GetMapping
  public List<Item> getAllItems() {
    return itemService.getAllItems(); // 서비스 레이어에서 모든 Item 객체를 가져옵니다.
  }

  /**
   * 특정 ID를 가진 Item 객체를 가져오는 메서드입니다.
   * 
   * @param id 검색할 Item 객체의 ID.
   * @return 요청한 ID의 Item 객체와 HTTP 상태 코드 200 OK를 포함하는 ResponseEntity 객체,
   *         또는 Item 객체가 존재하지 않을 경우 HTTP 상태 코드 404 Not Found를 포함하는 ResponseEntity
   *         객체.
   */
  @GetMapping("/{id}")
  public ResponseEntity<Item> getItemById(@PathVariable Long id) {
    Item item = itemService.getItemById(id); // 서비스 레이어에서 특정 ID의 Item 객체를 가져옵니다.
    return item != null ? new ResponseEntity<>(item, HttpStatus.OK) // Item 객체가 존재하면 200 OK 응답.
        : new ResponseEntity<>(HttpStatus.NOT_FOUND); // Item 객체가 존재하지 않으면 404 Not Found 응답.
  }

  /**
   * Item 객체를 생성하거나 업데이트하는 메서드입니다.
   * <p>
   * 이 메서드는 전달된 Item 객체의 ID에 따라 생성 또는 업데이트 작업을 수행합니다.
   * </p>
   * 
   * @param id   Item 객체의 ID (생성 시 null, 업데이트 시 존재하는 ID).
   * @param item 요청 본문에서 전달된 Item 객체.
   * @return 생성 또는 업데이트된 Item 객체와 HTTP 상태 코드 201 Created 또는 200 OK를 포함하는
   *         ResponseEntity 객체.
   */
  @PostMapping
  public ResponseEntity<Item> saveOrUpdateItem(@RequestBody Item item) {
    if (item.getId() != null) {
      Item existingItem = itemService.getItemById(item.getId()); // 서비스 레이어에서 기존 Item 객체를 가져옵니다.
      if (existingItem != null) {
        // 기존 Item 객체를 업데이트합니다.
        Item updatedItem = itemService.saveItem(item);
        return new ResponseEntity<>(updatedItem, HttpStatus.OK); // 업데이트된 Item 객체를 반환하고 200 OK 응답을 반환합니다.
      } else {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND); // ID가 존재하지만 아이템을 찾을 수 없는 경우 404 Not Found 응답을 반환합니다.
      }
    } else {
      // 새 Item 객체를 생성합니다.
      Item newItem = itemService.saveItem(item);
      return new ResponseEntity<>(newItem, HttpStatus.CREATED); // 새 Item 객체를 저장하고 201 Created 응답을 반환합니다.
    }
  }

  /**
   * 특정 ID를 가진 Item 객체를 삭제하는 메서드입니다.
   * <p>
   * TODO: 삭제 시 인증 및 로그 기록, 백업 처리 추가
   * - 삭제된 Item 정보를 로그로 기록합니다.
   * - 삭제된 데이터를 백업하여 필요시 복구할 수 있도록 합니다.
   * - 인증 로직을 추가하여 삭제 권한을 검증합니다.
   * </p>
   * 
   * @param id 삭제할 Item 객체의 ID.
   * @return HTTP 상태 코드 204 No Content를 포함하는 ResponseEntity 객체,
   *         또는 Item 객체가 존재하지 않을 경우 HTTP 상태 코드 404 Not Found를 포함하는 ResponseEntity
   *         객체.
   */
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteItem(@PathVariable Long id) {
    if (itemService.getItemById(id) != null) {
      // TODO: 로그 기록 및 백업 처리 로직 추가
      // 로그 기록: 삭제된 Item 정보를 로그로 기록합니다.
      // 백업: 삭제된 데이터를 백업하여 필요시 복구할 수 있도록 합니다.
      // TODO: 인증 로직 추가
      // 인증: 삭제 요청자가 권한이 있는지 검증합니다.
      itemService.deleteItem(id); // 서비스 레이어에서 해당 Item 객체를 삭제합니다.
      return new ResponseEntity<>(HttpStatus.NO_CONTENT); // 삭제 성공 시 204 No Content 응답을 반환합니다.
    }
    return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Item 객체가 존재하지 않으면 404 Not Found 응답을 반환합니다.
  }

  @GetMapping("/logs")
  public ResponseEntity<String> getLogFile() {
    try {
      String logs = itemService.readLogFile(); // 로그 파일 내용을 읽어옵니다.
      return new ResponseEntity<>(logs, HttpStatus.OK); // 로그 내용과 200 OK 응답을 반환합니다.
    } catch (IOException e) {
      return new ResponseEntity<>("Error reading log file: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  /**
   * 백업 파일의 내용을 조회하는 메서드입니다.
   * 
   * @return 백업 파일의 내용과 HTTP 상태 코드 200 OK를 포함하는 ResponseEntity 객체.
   */
  @GetMapping("/backups")
  public ResponseEntity<String> getBackupFile() {
    try {
      String backups = itemService.readBackupFile(); // 백업 파일 내용을 읽어옵니다.
      return new ResponseEntity<>(backups, HttpStatus.OK); // 백업 내용과 200 OK 응답을 반환합니다.
    } catch (IOException e) {
      return new ResponseEntity<>("Error reading backup file: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
}
