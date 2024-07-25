package com.fullstack.project.repository;

import com.fullstack.project.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Item 엔티티에 대한 데이터베이스 작업을 처리하는 리포지토리 인터페이스입니다.
 * <p>
 * Spring Data JPA의 JpaRepository를 상속받아 기본적인 CRUD (Create, Read, Update, Delete) 작업과
 * 추가적인 쿼리 메서드를 지원합니다.
 * </p>
 * 
 * @author Your Name
 * @version 1.0
 */
public interface ItemRepository extends JpaRepository<Item, Long> {

    /**
     * JpaRepository를 상속받아 Item 엔티티에 대한 데이터베이스 작업을 처리합니다.
     * <p>
     * JpaRepository는 기본적인 CRUD 작업을 위한 메서드들을 제공합니다.
     * 예를 들어, {@code save()}, {@code findById()}, {@code findAll()}, {@code deleteById()} 등이 있습니다.
     * </p>
     * 
     * @param <Item> 엔티티 타입.
     * @param <Long> 엔티티의 ID 타입.
     */
}
