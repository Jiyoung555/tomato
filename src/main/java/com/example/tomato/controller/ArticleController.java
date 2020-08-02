package com.example.tomato.controller;
import com.example.tomato.dto.ArticleForm;
import com.example.tomato.entity.Article;
import com.example.tomato.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Slf4j
@Controller
public class ArticleController {
    private final ArticleRepository articleRepository;

    @GetMapping("/articles")
    public String index(Model model){
        Iterable<Article> articles = articleRepository.findAll();
        model.addAttribute("articles", articles);

        model.addAttribute("msg", "안녕하세요. 반갑습니다.");
        return "articles/index";
    }

    @GetMapping("/articles/new")
    public String newArticle(){
        return "articles/new";
    }

    @GetMapping("/articles/{id}")
    public String show(@PathVariable Long id, Model model){
        Article article = articleRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("Article 없음")
        );

        model.addAttribute("article", article);

        model.addAttribute("comments", article.getComments());

        return "articles/show";
    }

    //자동 매크로
    @GetMapping("/articles/init")
    public String init(){
        List<Article> articleList = new ArrayList();

        for(int i = 0; i < 3; i++){
            articleList.add( new Article(null, "title" + i, "content" + i) );
        }

        articleRepository.saveAll(articleList);
        return "redirect:/articles";
    }

    //수정
    //@GetMapping("/articles/edit/{id}")
    //public String edit(@PathVariable Long id, Model model){
    //    Article entity = articleRepository.findById(id).orElseThrow(
    //            () -> new IllegalArgumentException("Article 없음")
    //    );

    //    model.addAttribute("article", entity);
    //    return "articles/edit";
    //}

    @GetMapping("/articles/edit/{id}")
    public String edit(@PathVariable Long id, Model model){
        Article entity = articleRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 Article이 없습니다.")
        );

        model.addAttribute("article", entity);
        return "articles/edit";
    }

}
