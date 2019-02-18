package com.melardev.spring.blogapi.dtos.response.articles;

import com.melardev.spring.blogapi.dtos.response.users.partials.UserIdAndUsernameDto;
import com.melardev.spring.blogapi.dtos.response.base.SuccessResponse;
import com.melardev.spring.blogapi.dtos.response.comments.partials.CommentPartialDto;
import com.melardev.spring.blogapi.entities.Article;
import com.melardev.spring.blogapi.entities.Comment;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class SingleArticleResponse extends SuccessResponse {
    private final String title;
    private final String slug;
    private final String description;
    private final Integer views;
    private final String body;
    private final ZonedDateTime createdAt;
    private final ZonedDateTime updatedAt;
    private final UserIdAndUsernameDto user;
    private final Collection<CommentPartialDto> comments;
    private final Long id;

    private SingleArticleResponse(Long id, String title, String slug, String description, String body,
                                  Integer views, ZonedDateTime createdAt, ZonedDateTime updatedAt, List<CommentPartialDto> comments, UserIdAndUsernameDto user) {
        this.id = id;
        this.title = title;
        this.slug = slug;
        this.description = description;
        this.body = body;
        this.views = views;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.user = user;
        this.comments = comments;
    }

    public static SingleArticleResponse build(Article article) {
        List<CommentPartialDto> commentPartialDtos = new ArrayList<>();
        for (Comment comment : article.getComments()) {
            commentPartialDtos.add(CommentPartialDto.build(comment));
        }
        return new SingleArticleResponse(
                article.getId(),
                article.getTitle(), article.getSlug(), article.getDescription(), article.getBody(),
                article.getViews(), article.getCreatedAt(), article.getUpdatedAt(),
                commentPartialDtos,
                UserIdAndUsernameDto.build(article.getUser())
        );
    }

    public String getTitle() {
        return title;
    }

    public String getSlug() {
        return slug;
    }

    public String getDescription() {
        return description;
    }

    public Integer getViews() {
        return views;
    }

    public String getBody() {
        return body;
    }

    public ZonedDateTime getCreatedAt() {
        return createdAt;
    }

    public ZonedDateTime getUpdatedAt() {
        return updatedAt;
    }

    public UserIdAndUsernameDto getUser() {
        return user;
    }

    public Long getId() {
        return id;
    }

    public Collection<CommentPartialDto> getComments() {
        return comments;
    }
}
