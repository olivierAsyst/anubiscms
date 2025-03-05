package lab.anubis.anubiscms.features.article.service;

import lab.anubis.anubiscms.features.article.dto.ArticleDto;
import lab.anubis.anubiscms.features.article.mapper.ArticleMapper;
import lab.anubis.anubiscms.features.article.model.Article;
import lab.anubis.anubiscms.features.article.repository.ArticleRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ArticleServiceImpl implements ArticleService{

    private final ArticleRepository articleRepository;
    private final ArticleMapper articleMapper;

    public ArticleServiceImpl(ArticleRepository articleRepository, ArticleMapper articleMapper) {
        this.articleRepository = articleRepository;
        this.articleMapper = articleMapper;
    }

    @Override
    public ArticleDto createArticle(ArticleDto dto, String username) {
        Article article = articleMapper.toEntity(dto);
        article.setOwnerUsername(username);
        article.setCreatedAt(LocalDateTime.now());
        article.setUpdatedAt(LocalDateTime.now());
        Article savedArticle = articleRepository.save(article);
        return articleMapper.toDto(savedArticle);
    }

    @Override
    public Optional<ArticleDto> updateArticle(Long idArticle, ArticleDto articleDto, String username) {
        return articleRepository.findById(idArticle).map(exist -> {
            exist.setTitle(articleDto.title());
            exist.setContent(articleDto.content());
            exist.setOwnerUsername(username);
            exist.setUpdatedAt(LocalDateTime.now());

            Article updateArticle = articleRepository.save(exist);
            return articleMapper.toDto(updateArticle);
        });
    }

    @Override
    public Optional<ArticleDto> getArticleById(Long idArticle) {
        return articleRepository.findById(idArticle)
                .map(articleMapper::toDto);
    }

    @Override
    public List<ArticleDto> getAllArticles() {
        return articleRepository.findAll()
                .stream()
                .map(articleMapper::toDto)
                .toList();
    }

    @Override
    public List<ArticleDto> getArticleByUser(String username) {
        return articleRepository.findByOwnerUsername(username)
                .stream()
                .map(articleMapper::toDto)
                .toList();
    }

    @Override
    public void deleteArticle(Long idArticle, String username) {
        articleRepository.deleteById(idArticle);
    }
}
