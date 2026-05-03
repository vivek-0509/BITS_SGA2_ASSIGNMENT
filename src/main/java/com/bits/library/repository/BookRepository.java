package com.bits.library.repository;

import com.bits.library.dto.BookAuthorView;
import com.bits.library.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    /**
     * Custom query that performs an INNER JOIN between books and authors
     * and returns a flattened projection of both entities.
     */
    @Query("""
            SELECT new com.bits.library.dto.BookAuthorView(
                b.id, b.title, b.isbn, b.publishedYear, b.genre,
                a.id, a.name, a.nationality)
            FROM Book b
            INNER JOIN b.author a
            ORDER BY a.name ASC, b.title ASC
            """)
    List<BookAuthorView> findAllBooksWithAuthors();
}
