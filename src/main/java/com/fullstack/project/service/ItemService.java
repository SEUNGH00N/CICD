package com.fullstack.project.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fullstack.project.model.Item;
import com.fullstack.project.repository.ItemRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Item 엔티티에 대한 비즈니스 로직을 처리하는 서비스 클래스입니다.
 * <p>
 * 이 클래스는 ItemRepository를 사용하여 데이터베이스와 상호작용하고,
 * 클라이언트 요청에 대한 비즈니스 로직을 구현합니다.
 * </p>
 * 
 * @version 1.0
 */
@Service
public class ItemService {

  private static final Logger LOGGER = LoggerFactory.getLogger(ItemService.class);
  private static final String BACKUP_FILE_PATH = "backup.log"; // 백업 파일 경로
  private static final String LOG_FILE_PATH = "item-changes.log"; // 로그 파일 경로

  @Autowired
  private ItemRepository itemRepository; // ItemRepository를 주입 받아 사용합니다.

  /**
   * 데이터베이스에서 모든 Item 객체를 조회하는 메서드입니다.
   * 
   * @return 모든 Item 객체의 리스트.
   */
  public List<Item> getAllItems() {
    return itemRepository.findAll(); // ItemRepository를 통해 모든 Item 객체를 조회합니다.
  }

  /**
   * 주어진 ID를 가진 Item 객체를 조회하는 메서드입니다.
   * 
   * @param id 조회할 Item 객체의 ID.
   * @return 요청한 ID의 Item 객체, 존재하지 않을 경우 null.
   */
  public Item getItemById(Long id) {
    return itemRepository.findById(id).orElse(null); // ID로 Item 객체를 조회하고, 존재하지 않을 경우 null을 반환합니다.
  }

  /**
   * Item 객체를 저장하거나 업데이트하는 메서드입니다.
   * 
   * @param item 저장할 Item 객체.
   * @return 저장된 Item 객체.
   */
  @Transactional
  public Item saveItem(Item item) {
    Item savedItem = itemRepository.save(item); // Item 객체를 저장하거나 업데이트합니다.
    logItemChange("Saved or updated Item: " + savedItem.toString()); // Item 객체를 로그로 기록합니다.
    backupItem(savedItem); // 저장된 Item 객체를 백업합니다.
    return savedItem;
  }

  /**
   * 주어진 ID를 가진 Item 객체를 삭제하는 메서드입니다.
   * 
   * @param id 삭제할 Item 객체의 ID.
   */
  @Transactional
  public void deleteItem(Long id) {
    Item item = itemRepository.findById(id).orElse(null); // 삭제할 Item 객체를 조회합니다.

    if (item != null) {
      logItemChange("Deleted Item: " + item.toString()); // 삭제된 Item 객체를 로그로 기록합니다.
      backupItem(item); // 삭제된 Item 객체를 백업합니다.
      itemRepository.deleteById(id); // ID로 Item 객체를 삭제합니다.
    } else {
      throw new ItemNotFoundException("Item not found for ID: " + id); // 사용자 정의 예외를 던집니다.
    }
  }

  /**
   * Item 객체의 변경 사항을 로그 파일에 기록하는 메서드입니다.
   * 
   * @param message 로그 메시지.
   */
  private void logItemChange(String message) {
    LOGGER.info(message); // 콘솔 로그 출력
    try (FileWriter writer = new FileWriter(LOG_FILE_PATH, true)) { // 로그 파일에 기록
      writer.write(message + System.lineSeparator());
    } catch (IOException e) {
      LOGGER.error("Error writing log to file: " + e.getMessage());
    }
  }

  /**
   * Item 객체를 백업하는 메서드입니다.
   * 
   * @param item 백업할 Item 객체.
   */
  private void backupItem(Item item) {
    ObjectMapper objectMapper = new ObjectMapper();
    try {
      String itemJson = objectMapper.writeValueAsString(item); // JSON 문자열로 변환
      try (FileWriter fileWriter = new FileWriter(BACKUP_FILE_PATH, true)) { // 파일에 기록
        fileWriter.write(itemJson + System.lineSeparator());
      }
      LOGGER.info("Item backed up to file: " + BACKUP_FILE_PATH);
    } catch (IOException e) {
      LOGGER.error("Error occurred while backing up Item: " + item, e); // 오류 발생 시 error 레벨로 로그 기록
    }
  }

  /**
   * 로그 파일의 내용을 문자열로 읽어오는 메서드입니다.
   * 
   * @return 로그 파일의 내용.
   * @throws IOException 로그 파일을 읽는 동안 발생한 예외.
   */
  public String readLogFile() throws IOException {
    try (BufferedReader reader = new BufferedReader(new FileReader(LOG_FILE_PATH))) {
      return reader.lines().collect(Collectors.joining(System.lineSeparator()));
    }
  }

  /**
   * 백업 파일의 내용을 문자열로 읽어오는 메서드입니다.
   * 
   * @return 백업 파일의 내용.
   * @throws IOException 백업 파일을 읽는 동안 발생한 예외.
   */
  public String readBackupFile() throws IOException {
    try (BufferedReader reader = new BufferedReader(new FileReader(BACKUP_FILE_PATH))) {
      return reader.lines().collect(Collectors.joining(System.lineSeparator()));
    }
  }

  /**
   * 사용자 정의 예외 클래스: Item 객체가 존재하지 않을 경우 발생합니다.
   */
  public static class ItemNotFoundException extends RuntimeException {
    public ItemNotFoundException(String message) {
      super(message);
    }
  }
}
