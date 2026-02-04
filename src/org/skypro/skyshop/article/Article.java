package org.skypro.skyshop.article;

import org.skypro.skyshop.search.Searchable;
public class Article implements Searchable {
    private final String title;
    private final String content;

    /**
     * Конструктор статьи
     * @param title заголовок статьи
     * @param content текст статьи
     * @throws IllegalArgumentException если заголовок или текст невалидны
     */
    public Article(String title, String content) {
        validateTitle(title);
        validateContent(content);
        this.title = title;
        this.content = content;
    }

    /**
     * Проверяет валидность заголовка
     * @param title заголовок для проверки
     * @throws IllegalArgumentException если заголовок невалидный
     */
    private void validateTitle(String title) {
        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException(
                    "Заголовок статьи не может быть null, пустой строкой или состоять только из пробелов"
            );
        }
    }

    /**
     * Проверяет валидность текста статьи
     * @param content текст для проверки
     * @throws IllegalArgumentException если текст невалидный
     */
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

    /**
     * Получить заголовок статьи
     * @return заголовок статьи
     */
    public String getTitle() {
        return title;
    }

    /**
     * Получить текст статьи
     * @return текст статьи
     */
    public String getContent() {
        return content;
    }

    /**
     * Получить имя объекта (заголовок статьи)
     * @return заголовок статьи
     */
    @Override
    public String getName() {
        return title;
    }

    /**
     * Получить термин для поиска
     * @return строка из заголовка и текста статьи
     */
    @Override
    public String getSearchTerm() {
        return toString();
    }

    /**
     * Получить тип контента
     * @return "ARTICLE"
     */
    @Override
    public String getContentType() {
        return "ARTICLE";
    }

    /**
     * Строковое представление статьи
     * @return строка формата "Заголовок\nТекст"
     */
    @Override
    public String toString() {
        return title + "\n" + content;
    }
}
