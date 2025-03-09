package lab.anubis.anubiscms.features.article.controller;

import lab.anubis.anubiscms.features.article.dto.ArticleDto;
import lab.anubis.anubiscms.features.article.service.ArticleService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/articles")
public class ArticleController {

    private final ArticleService articleService;

    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @PostMapping
    public ArticleDto createArticle(@RequestBody ArticleDto articleDto,
                                    @AuthenticationPrincipal UserDetails userDetails){
        String username = userDetails.getUsername();
        return articleService.createArticle(articleDto, username);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('USER_ADMIN')")
    public Optional<ArticleDto> updateArticle(@PathVariable Long id, @RequestBody ArticleDto articleDto,
                                              @AuthenticationPrincipal UserDetails userDetails){
        return articleService.updateArticle(id, articleDto, userDetails.getUsername());
    }


    @GetMapping("/all")
    public List<ArticleDto> getAllArticles(@AuthenticationPrincipal UserDetails userDetails){
        return articleService.getAllArticles();
    }

    @GetMapping
    public List<ArticleDto> getAllArticlesByUsername(@AuthenticationPrincipal UserDetails userDetails){
        return articleService.getArticleByUser(userDetails.getUsername());
    }

    @GetMapping("/{id}")
    public Optional<ArticleDto> getArticleById(@PathVariable Long id, @AuthenticationPrincipal UserDetails userDetails){
        return articleService.getArticleById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteArticle(@PathVariable Long id, @AuthenticationPrincipal UserDetails userDetails){
        articleService.deleteArticle(id, userDetails.getUsername());
    }
}
