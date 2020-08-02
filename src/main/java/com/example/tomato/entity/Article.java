package com.example.tomato.entity;
import com.example.tomato.dto.ArticleForm;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
public class Article extends BaseTime{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    //Comment를 여러개 담을 리스트 타입의 필드
    // (하나의 Article은 여러개의 Comment를 가질 수 있다)
    // fetch: 연결 방법 설정 (무시)
    // mappedBy: comments를 article에 연결시키겠다
    //애노태이션 OneToMany : 일대다 관계를 만들겠다
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "article")
    private List<Comment> comments;

    @Override
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", comments=" + (comments == null ? null : comments.size()) +
                '}';
    }

    @Builder
    public Article(Long id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }

    public void rewrite(String title, String content) {
        this.title = title;
        this.content = content;
    }


}