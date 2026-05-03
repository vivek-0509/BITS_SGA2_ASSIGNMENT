package com.bits.library.service;

import com.bits.library.entity.Author;
import com.bits.library.repository.AuthorRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class AuthorService {

    private final AuthorRepository authorRepository;

    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Transactional(readOnly = true)
    public List<Author> findAll() {
        return authorRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<Author> findById(Long id) {
        return authorRepository.findById(id);
    }

    public Author save(Author author) {
        return authorRepository.save(author);
    }

    public Author update(Long id, Author updated) {
        Author existing = authorRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Author not found with id " + id));
        existing.setName(updated.getName());
        existing.setNationality(updated.getNationality());
        existing.setBirthYear(updated.getBirthYear());
        return authorRepository.save(existing);
    }

    public void deleteById(Long id) {
        authorRepository.deleteById(id);
    }
}
