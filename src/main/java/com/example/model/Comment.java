package com.example.model;

import javax.persistence.*;

@Entity
@Table(name = "comments") // 테이블 이름이 'comments'인 경우
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 자동 증가 설정
    private Long id;

    private String content;

    @ManyToOne
    @JoinColumn(name = "post_id", nullable = false) // 게시물과의 관계 설정
    private Post post;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false) // 작성자와의 관계 설정
    private User user;

    // 기본 생성자
    public Comment() {}

    // 생성자, getter 및 setter 메서드
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
