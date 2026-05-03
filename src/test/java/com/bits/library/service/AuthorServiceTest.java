package com.bits.library.service;

import com.bits.library.entity.Author;
import com.bits.library.repository.AuthorRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AuthorServiceTest {

    @Mock
    private AuthorRepository authorRepository;

    @InjectMocks
    private AuthorService authorService;

    @Test
    void findAll_returnsAllAuthors() {
        when(authorRepository.findAll()).thenReturn(List.of(
                new Author("A1", "X", 1900),
                new Author("A2", "Y", 1910)
        ));

        List<Author> result = authorService.findAll();

        assertThat(result).hasSize(2);
        verify(authorRepository, times(1)).findAll();
    }

    @Test
    void update_changesNameNationalityAndBirthYear() {
        Author existing = new Author("Old Name", "Old", 1900);
        existing.setId(7L);
        Author changes = new Author("New Name", "New", 1950);

        when(authorRepository.findById(7L)).thenReturn(Optional.of(existing));
        when(authorRepository.save(any(Author.class))).thenAnswer(inv -> inv.getArgument(0));

        Author result = authorService.update(7L, changes);

        assertThat(result.getName()).isEqualTo("New Name");
        assertThat(result.getNationality()).isEqualTo("New");
        assertThat(result.getBirthYear()).isEqualTo(1950);
    }

    @Test
    void update_whenAuthorMissing_throws() {
        when(authorRepository.findById(404L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> authorService.update(404L, new Author("X", "Y", 1900)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Author not found");
    }
}
