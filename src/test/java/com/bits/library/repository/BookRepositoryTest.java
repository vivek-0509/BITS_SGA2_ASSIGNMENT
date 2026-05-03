package com.bits.library.repository;

import com.bits.library.dto.BookAuthorView;
import com.bits.library.entity.Author;
import com.bits.library.entity.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase
class BookRepositoryTest {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @Test
    void findAllBooksWithAuthors_returnsInnerJoinResults() {
        Author tolkien = authorRepository.save(new Author("J.R.R. Tolkien", "British", 1892));
        Author rowling = authorRepository.save(new Author("J.K. Rowling", "British", 1965));

        bookRepository.save(new Book("The Hobbit", "ISBN-1", 1937, "Fantasy", tolkien));
        bookRepository.save(new Book("Harry Potter", "ISBN-2", 1997, "Fantasy", rowling));
        // Author with no books — must NOT appear because of INNER JOIN
        authorRepository.save(new Author("Lonely Author", "Unknown", 2000));

        List<BookAuthorView> rows = bookRepository.findAllBooksWithAuthors();

        assertThat(rows).hasSize(2);
        assertThat(rows).extracting(BookAuthorView::getAuthorName)
                .containsExactlyInAnyOrder("J.R.R. Tolkien", "J.K. Rowling");
        assertThat(rows).extracting(BookAuthorView::getTitle)
                .containsExactlyInAnyOrder("The Hobbit", "Harry Potter");
    }

    @Test
    void save_persistsBookAndAssignsId() {
        Author author = authorRepository.save(new Author("Test Author", "Test", 1900));
        Book book = bookRepository.save(new Book("Test Title", "ISBN-T", 2024, "Test", author));

        assertThat(book.getId()).isNotNull();
        assertThat(bookRepository.findById(book.getId())).isPresent();
    }
}
