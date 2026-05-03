package com.bits.library.service;

import com.bits.library.dto.BookAuthorView;
import com.bits.library.entity.Author;
import com.bits.library.entity.Book;
import com.bits.library.repository.AuthorRepository;
import com.bits.library.repository.BookRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    public BookService(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    @Transactional(readOnly = true)
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<Book> findById(Long id) {
        return bookRepository.findById(id);
    }

    /**
     * Returns the result of an INNER JOIN between Book and Author.
     */
    @Transactional(readOnly = true)
    public List<BookAuthorView> findAllBooksWithAuthors() {
        return bookRepository.findAllBooksWithAuthors();
    }

    public Book save(Book book, Long authorId) {
        Author author = authorRepository.findById(authorId)
                .orElseThrow(() -> new IllegalArgumentException("Author not found with id " + authorId));
        book.setAuthor(author);
        try {
            return bookRepository.save(book);
        } catch (DataIntegrityViolationException ex) {
            throw new IllegalStateException(
                    "Could not save book — possibly a duplicate ISBN: " + book.getIsbn(), ex);
        }
    }

    public Book update(Long id, Book updated, Long authorId) {
        Book existing = bookRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Book not found with id " + id));
        Author author = authorRepository.findById(authorId)
                .orElseThrow(() -> new IllegalArgumentException("Author not found with id " + authorId));

        existing.setTitle(updated.getTitle());
        existing.setIsbn(updated.getIsbn());
        existing.setPublishedYear(updated.getPublishedYear());
        existing.setGenre(updated.getGenre());
        existing.setAuthor(author);
        try {
            return bookRepository.save(existing);
        } catch (DataIntegrityViolationException ex) {
            throw new IllegalStateException(
                    "Could not update book — possibly a duplicate ISBN: " + updated.getIsbn(), ex);
        }
    }

    public void deleteById(Long id) {
        bookRepository.deleteById(id);
    }
}
