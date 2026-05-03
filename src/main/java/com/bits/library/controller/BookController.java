package com.bits.library.controller;

import com.bits.library.entity.Book;
import com.bits.library.service.AuthorService;
import com.bits.library.service.BookService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;
    private final AuthorService authorService;

    public BookController(BookService bookService, AuthorService authorService) {
        this.bookService = bookService;
        this.authorService = authorService;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("rows", bookService.findAllBooksWithAuthors());
        return "books/list";
    }

    @GetMapping("/new")
    public String addForm(Model model) {
        model.addAttribute("book", new Book());
        model.addAttribute("authors", authorService.findAll());
        return "books/add";
    }

    @PostMapping
    public String create(@Valid @ModelAttribute("book") Book book,
                         BindingResult bindingResult,
                         @RequestParam("authorId") Long authorId,
                         Model model,
                         RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("authors", authorService.findAll());
            return "books/add";
        }
        try {
            bookService.save(book, authorId);
            redirectAttributes.addFlashAttribute("success", "Book created successfully.");
            return "redirect:/books";
        } catch (IllegalStateException | IllegalArgumentException ex) {
            model.addAttribute("authors", authorService.findAll());
            model.addAttribute("error", ex.getMessage());
            return "books/add";
        }
    }

    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes) {
        return bookService.findById(id)
                .map(b -> {
                    model.addAttribute("book", b);
                    model.addAttribute("authors", authorService.findAll());
                    return "books/edit";
                })
                .orElseGet(() -> {
                    redirectAttributes.addFlashAttribute("error", "Book not found.");
                    return "redirect:/books";
                });
    }

    @PostMapping("/{id}")
    public String update(@PathVariable Long id,
                         @Valid @ModelAttribute("book") Book book,
                         BindingResult bindingResult,
                         @RequestParam("authorId") Long authorId,
                         Model model,
                         RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("authors", authorService.findAll());
            return "books/edit";
        }
        try {
            bookService.update(id, book, authorId);
            redirectAttributes.addFlashAttribute("success", "Book updated successfully.");
            return "redirect:/books";
        } catch (IllegalStateException | IllegalArgumentException ex) {
            model.addAttribute("authors", authorService.findAll());
            model.addAttribute("error", ex.getMessage());
            return "books/edit";
        }
    }
}
