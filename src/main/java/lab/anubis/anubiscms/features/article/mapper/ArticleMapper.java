package lab.anubis.anubiscms.features.article.mapper;

import lab.anubis.anubiscms.features.article.dto.ArticleDto;
import lab.anubis.anubiscms.features.article.model.Article;
import org.springframework.stereotype.Service;

@Service
public class ArticleMapper {

    public ArticleDto toDto(Article article){
        if (article == null) return null;
        return new ArticleDto(
                article.getId(),
                article.getTitle(),
                article.getContent(),
                article.getOwnerUsername(),
                article.getCreatedAt(),
                article.getUpdatedAt()
        );
    }

    public Article toEntity(ArticleDto dto){
        if (dto == null) return null;
        Article article = new Article();
        article.setId(dto.id());
        article.setTitle(dto.title());
        article.setContent(dto.content());
        article.setCreatedAt(dto.createdAt());
        article.setUpdatedAt(dto.updatedAt());

        return article;
    }

}
