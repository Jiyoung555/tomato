package com.example.tomato.api;
import com.example.tomato.dto.CommentForm;
import com.example.tomato.entity.Article;
import com.example.tomato.entity.Comment;
import com.example.tomato.repository.ArticleRepository;
import com.example.tomato.repository.CommentRepository;
import com.example.tomato.service.CommentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RestController
public class CommentApiController {
    private final CommentService commentService;

    @PostMapping("/api/comments/{articleId}")
    public Long create(@PathVariable Long articleId,
                       @RequestBody CommentForm form) {
        // 서비스 객체가 댓글 생성
        Comment saved = commentService.create(articleId, form);

        log.info("saved: " + saved.toString());
        return saved.getId();
    }

    @PutMapping("/api/comments/{id}")
    public Long update(@PathVariable Long id,
                       @RequestBody CommentForm form) {
        // 서비스 객체가 댓글 수정
        Comment updated = commentService.update(id, form);
        return updated.getId();
    }

}
