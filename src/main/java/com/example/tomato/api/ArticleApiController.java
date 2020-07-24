package com.example.tomato.api;
import com.example.tomato.dto.ArticleForm;
import com.example.tomato.entity.Article;
import com.example.tomato.repository.ArticleRepository;
import com.example.tomato.service.ArticleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Slf4j
@RestController
public class ArticleApiController {

    private final ArticleRepository articleRepository;
    private final ArticleService articleService;

    @PostMapping("/api/articles")
    public Long create(@RequestBody ArticleForm form){
        log.info(form.toString());

        Article article = form.toEntity();

        Article saved = articleRepository.save(article);
        return saved.getId();
    }

    @GetMapping("/api/articles/{id}")
    public ArticleForm getArticle(@PathVariable Long id){
        Article entity = articleRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("Article 없음")
        );

        return new ArticleForm(entity);
    }

    @GetMapping("/api/articles")
    public List<ArticleForm> getArticles(){
        List<ArticleForm> articleFormList = new ArrayList();

        Iterable<Article> entites = articleRepository.findAll();

        for(Article entity : entites){
            ArticleForm dto = new ArticleForm(entity);
            articleFormList.add(dto);
        }

        return articleFormList;
    }

    //수정
    @PutMapping("/api/articles/{id}")
    public Long update(@PathVariable Long id, @RequestBody ArticleForm form){
        Article saved = articleService.update(id, form);
        return saved.getId();
    }

    //삭제
    @DeleteMapping("/api/articles/{id}")
    public Long destroy(@PathVariable Long id){
        return articleService.destroy(id);
    }
}
