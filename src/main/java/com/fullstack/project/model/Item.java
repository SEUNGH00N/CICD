package com.fullstack.project.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import java.util.HashSet;
import java.util.Set;

/**
 * 데이터베이스의 Item 테이블과 매핑되는 JPA 엔티티 클래스입니다.
 * <p>
 * 이 클래스는 Item 객체를 데이터베이스에 저장하고 조회하기 위해 사용됩니다.
 * </p>
 */
@Entity // 이 클래스가 JPA 엔티티임을 나타내는 어노테이션입니다.
public class Item {

  @Id // 이 필드가 엔티티의 기본 키임을 나타냅니다.
  @GeneratedValue(strategy = GenerationType.IDENTITY) // 기본 키의 값을 자동으로 생성하는 전략을 지정합니다.
  private Long id; // Item 객체의 고유 식별자

  private String name; // Item의 이름
  private String description; // Item의 설명

  @ElementCollection // 이 필드가 기본 타입 또는 임베디드 타입의 컬렉션임을 나타냅니다.
  @CollectionTable(name = "item_tags", joinColumns = @JoinColumn(name = "item_id")) // 태그를 저장할 별도의 테이블을 정의합니다.
  @Column(name = "tag") // 태그 컬럼의 이름을 정의합니다.
  private Set<String> tags = new HashSet<>(); // 태그를 저장하는 Set 컬렉션

  /**
   * 기본 생성자입니다.
   * <p>
   * JPA는 엔티티를 인스턴스화할 때 기본 생성자를 필요로 합니다.
   * </p>
   */
  public Item() {
  }

  /**
   * 모든 필드를 초기화하는 생성자입니다.
   * 
   * @param name        Item의 이름
   * @param description Item의 설명
   */
  public Item(String name, String description) {
    this.name = name;
    this.description = description;
  }

  /**
   * Item 객체의 ID를 반환합니다.
   * 
   * @return Item의 ID
   */
  public Long getId() {
    return id;
  }

  /**
   * Item 객체의 ID를 설정합니다.
   * 
   * @param id Item의 ID
   */
  public void setId(Long id) {
    this.id = id;
  }

  /**
   * Item 객체의 이름을 반환합니다.
   * 
   * @return Item의 이름
   */
  public String getName() {
    return name;
  }

  /**
   * Item 객체의 이름을 설정합니다.
   * 
   * @param name Item의 이름
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * Item 객체의 설명을 반환합니다.
   * 
   * @return Item의 설명
   */
  public String getDescription() {
    return description;
  }

  /**
   * Item 객체의 설명을 설정합니다.
   * 
   * @param description Item의 설명
   */
  public void setDescription(String description) {
    this.description = description;
  }

  /**
   * Item의 태그를 반환합니다.
   * 
   * @return 태그의 집합
   */
  public Set<String> getTags() {
    return tags;
  }

  /**
   * Item의 태그를 설정합니다.
   * 
   * @param tags 태그의 집합
   */
  public void setTags(Set<String> tags) {
    this.tags = tags;
  }

  /**
   * Item에 태그를 추가합니다.
   * 
   * @param tag 추가할 태그
   */
  public void addTag(String tag) {
    this.tags.add(tag);
  }

  /**
   * Item에서 태그를 제거합니다.
   * 
   * @param tag 제거할 태그
   */
  public void removeTag(String tag) {
    this.tags.remove(tag);
  }
}
