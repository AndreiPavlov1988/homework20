package org.skypro.skyshop.article;

import org.skypro.skyshop.search.Searchable;
import java.util.Objects;

public class Article implements Searchable {
    private final String title;
    private final String content;


    public Article(String title, String content) {
        validateTitle(title);
        validateContent(content);
        this.title = title;
        this.content = content;
    }


    private void validateTitle(String title) {
        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException(
                    "Заголовок статьи не может быть null, пустой строкой или состоять только из пробелов"
            );
        }
    }


    private void validateContent(String content) {
        if (content == null) {
            throw new IllegalArgumentException(
                    "Текст статьи не может быть null"
            );
        }
        if (content.isBlank()) {
            throw new IllegalArgumentException(
                    "Текст статьи не может быть пустой строкой или состоять только из пробелов"
            );
        }
    }


    public String getTitle() {
        return title;
    }


    public String getContent() {
        return content;
    }


    @Override
    public String getName() {
        return title;
    }


    @Override
    public String getSearchTerm() {
        return toString();
    }


    @Override
    public String getContentType() {
        return "ARTICLE";
    }


    @Override
    public String toString() {
        return title + "\n" + content;
    }

      @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Article article = (Article) obj;
        return Objects.equals(title, article.title);
    }


    @Override
    public int hashCode() {
        return Objects.hash(title);
    }
}