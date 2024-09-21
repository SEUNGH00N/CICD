package com.example.model;

import javax.persistence.*;

@Entity
@Table(name = "posts") // 테이블 이름이 'posts'인 경우
public class Post {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 자동 증가 설정
    private Long id;

    private String title;
    private String content;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false) // 작성자와의 관계 설정
    private User user;

    // 기본 생성자
    public Post() {}

    // 생성자, getter 및 setter 메서드
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
