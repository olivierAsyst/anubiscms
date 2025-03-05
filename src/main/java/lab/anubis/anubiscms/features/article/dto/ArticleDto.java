package lab.anubis.anubiscms.features.article.dto;

import java.time.LocalDateTime;

public record ArticleDto(
        Long id,
        String title,
        String content,
        String ownerUsername,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
