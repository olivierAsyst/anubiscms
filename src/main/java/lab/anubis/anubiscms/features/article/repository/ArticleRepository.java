package lab.anubis.anubiscms.features.article.repository;

import lab.anubis.anubiscms.features.article.model.Article;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ArticleRepository extends JpaRepository<Article, Long> {
    List<Article> findByOwnerUsername(String ownerUsernema);
}
