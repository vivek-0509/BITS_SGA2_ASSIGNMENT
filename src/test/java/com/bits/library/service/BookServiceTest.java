package com.bits.library.service;

import com.bits.library.dto.BookAuthorView;
import com.bits.library.entity.Author;
import com.bits.library.entity.Book;
import com.bits.library.repository.AuthorRepository;
import com.bits.library.repository.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BookServiceTest {

    @Mock
    private BookRepository bookRepository;

    @Mock
    private AuthorRepository authorRepository;

    @InjectMocks
    private BookService bookService;

    private Author author;

    @BeforeEach
    void setUp() {
        author = new Author("Test Author", "Test", 1900);
        author.setId(1L);
    }

    @Test
    void save_assignsAuthorBeforePersisting() {
        Book book = new Book("Title", "ISBN-1", 2024, "Genre", null);
        when(authorRepository.findById(1L)).thenReturn(Optional.of(author));
        when(bookRepository.save(any(Book.class))).thenAnswer(inv -> inv.getArgument(0));

        Book saved = bookService.save(book, 1L);

        assertThat(saved.getAuthor()).isEqualTo(author);
        verify(bookRepository, times(1)).save(book);
    }

    @Test
    void save_whenAuthorMissing_throwsAndDoesNotPersist() {
        Book book = new Book("Title", "ISBN-1", 2024, "Genre", null);
        when(authorRepository.findById(99L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> bookService.save(book, 99L))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Author not found");

        verify(bookRepository, never()).save(any());
    }

    @Test
    void save_whenIsbnDuplicate_wrapsIntegrityViolation() {
        Book book = new Book("Title", "ISBN-DUP", 2024, "Genre", null);
        when(authorRepository.findById(1L)).thenReturn(Optional.of(author));
        when(bookRepository.save(any(Book.class)))
                .thenThrow(new DataIntegrityViolationException("duplicate isbn"));

        assertThatThrownBy(() -> bookService.save(book, 1L))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("duplicate ISBN");
    }

    @Test
    void update_modifiesExistingBook() {
        Book existing = new Book("Old", "OLD-ISBN", 2000, "Old", author);
        existing.setId(5L);
        Book changes = new Book("New", "NEW-ISBN", 2024, "New", null);

        when(bookRepository.findById(5L)).thenReturn(Optional.of(existing));
        when(authorRepository.findById(1L)).thenReturn(Optional.of(author));
        when(bookRepository.save(any(Book.class))).thenAnswer(inv -> inv.getArgument(0));

        Book result = bookService.update(5L, changes, 1L);

        assertThat(result.getTitle()).isEqualTo("New");
        assertThat(result.getIsbn()).isEqualTo("NEW-ISBN");
        assertThat(result.getPublishedYear()).isEqualTo(2024);
        assertThat(result.getGenre()).isEqualTo("New");
        assertThat(result.getAuthor()).isEqualTo(author);
    }

    @Test
    void findAllBooksWithAuthors_delegatesToRepository() {
        BookAuthorView v = new BookAuthorView(1L, "T", "I", 2000, "G", 1L, "Test Author", "Test");
        when(bookRepository.findAllBooksWithAuthors()).thenReturn(List.of(v));

        List<BookAuthorView> result = bookService.findAllBooksWithAuthors();

        assertThat(result).hasSize(1);
        assertThat(result.get(0).getAuthorName()).isEqualTo("Test Author");
    }
}
