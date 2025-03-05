package lab.anubis.anubiscms.features.article.service;

import lab.anubis.anubiscms.features.article.dto.ArticleDto;

import java.util.List;
import java.util.Optional;

public interface ArticleService {

    ArticleDto createArticle(ArticleDto dto, String username);
    Optional<ArticleDto> getArticleById(Long idArticle);
    List<ArticleDto> getAllArticles();
    List<ArticleDto> getArticleByUser(String username);
    Optional<ArticleDto> updateArticle(Long idArticle, ArticleDto articleDto, String username);
    void deleteArticle(Long idArticle, String username);

}
