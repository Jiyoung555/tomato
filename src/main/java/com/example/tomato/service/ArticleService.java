package com.example.tomato.service;
import com.example.tomato.dto.ArticleForm;
import com.example.tomato.entity.Article;
import com.example.tomato.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;

@Slf4j
@RequiredArgsConstructor
@Service
public class ArticleService {

    private final ArticleRepository articleRepository;

    @Transactional
    public Article update(Long id, ArticleForm form) {

        Article target = articleRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 Article이 없습니다.")
        );

        target.rewrite(form.getTitle(), form.getContent());

        Article saved = articleRepository.save(target);
        log.info(saved.toString() + "수정 및 db 저장 완료");

        return saved;
    }

    @Transactional
    public Long destroy(Long id) {
        Article target = articleRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 Article 없음")
        );

        articleRepository.delete(target);
        log.info(target.toString() + "삭제 성공");

        return target.getId();
    }
}
