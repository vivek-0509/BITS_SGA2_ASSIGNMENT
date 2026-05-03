package com.bits.library.config;

import com.bits.library.entity.Author;
import com.bits.library.entity.Book;
import com.bits.library.repository.AuthorRepository;
import com.bits.library.repository.BookRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class DataInitializer {

    @Bean
    public CommandLineRunner seedData(AuthorRepository authorRepository, BookRepository bookRepository) {
        return args -> {
            if (authorRepository.count() > 0) {
                return;
            }

            List<Author> authors = List.of(
                    new Author("J.K. Rowling", "British", 1965),
                    new Author("George R.R. Martin", "American", 1948),
                    new Author("J.R.R. Tolkien", "British", 1892),
                    new Author("Agatha Christie", "British", 1890),
                    new Author("Stephen King", "American", 1947),
                    new Author("Haruki Murakami", "Japanese", 1949),
                    new Author("Chimamanda Ngozi Adichie", "Nigerian", 1977),
                    new Author("Gabriel Garcia Marquez", "Colombian", 1927),
                    new Author("Arundhati Roy", "Indian", 1961),
                    new Author("Jane Austen", "British", 1775)
            );
            authorRepository.saveAll(authors);

            List<Book> books = List.of(
                    new Book("Harry Potter and the Sorcerer's Stone", "9780747532699", 1997, "Fantasy", authors.get(0)),
                    new Book("A Game of Thrones", "9780553103540", 1996, "Fantasy", authors.get(1)),
                    new Book("The Hobbit", "9780547928227", 1937, "Fantasy", authors.get(2)),
                    new Book("Murder on the Orient Express", "9780062073495", 1934, "Mystery", authors.get(3)),
                    new Book("The Shining", "9780307743657", 1977, "Horror", authors.get(4)),
                    new Book("Norwegian Wood", "9780375704024", 1987, "Romance", authors.get(5)),
                    new Book("Half of a Yellow Sun", "9781400095209", 2006, "Historical Fiction", authors.get(6)),
                    new Book("One Hundred Years of Solitude", "9780060883287", 1967, "Magical Realism", authors.get(7)),
                    new Book("The God of Small Things", "9780812979657", 1997, "Literary Fiction", authors.get(8)),
                    new Book("Pride and Prejudice", "9780141439518", 1813, "Romance", authors.get(9))
            );
            bookRepository.saveAll(books);
        };
    }
}
