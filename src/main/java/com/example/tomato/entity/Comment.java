package com.example.tomato.entity;
import lombok.*;

import javax.persistence.*;

@Getter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor // 모든 필드 포함 생성자 자동 기입
@Entity
public class Comment extends BaseTime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 20, nullable = false)
    private String author;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    @ManyToOne // 하나의 Article에 여러 Comment들을 연결시키겠다 (다대일)
    @JoinColumn(name = "article_id") // FK를 토대로 합치겠다는 뜻. FK의 컬럼 이름은 article_id
    private Article article;

    // 해당 댓글이 어느 게시글에 작성된 것인지를 기록!
    public void stickTo(Article article) {
        this.article = article;
    }

    public void rewrite(String content) {
        this.content = content;
    }
}
