package com.bits.library.dto;

public class BookAuthorView {

    private Long bookId;
    private String title;
    private String isbn;
    private Integer publishedYear;
    private String genre;
    private Long authorId;
    private String authorName;
    private String nationality;

    public BookAuthorView(Long bookId, String title, String isbn, Integer publishedYear, String genre,
                          Long authorId, String authorName, String nationality) {
        this.bookId = bookId;
        this.title = title;
        this.isbn = isbn;
        this.publishedYear = publishedYear;
        this.genre = genre;
        this.authorId = authorId;
        this.authorName = authorName;
        this.nationality = nationality;
    }

    public Long getBookId() {
        return bookId;
    }

    public String getTitle() {
        return title;
    }

    public String getIsbn() {
        return isbn;
    }

    public Integer getPublishedYear() {
        return publishedYear;
    }

    public String getGenre() {
        return genre;
    }

    public Long getAuthorId() {
        return authorId;
    }

    public String getAuthorName() {
        return authorName;
    }

    public String getNationality() {
        return nationality;
    }
}
