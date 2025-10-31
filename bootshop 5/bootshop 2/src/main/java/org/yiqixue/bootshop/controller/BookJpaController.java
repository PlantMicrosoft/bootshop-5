package org.yiqixue.bootshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.yiqixue.bootshop.entity.BookJpa;
import org.yiqixue.bootshop.service.BookJpaService;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/jpa/books")
public class BookJpaController {
    
    @Autowired
    private BookJpaService bookJpaService;
    
    @GetMapping
    public List<BookJpa> getAllBooks() {
        return bookJpaService.getAllBooks();
    }
    
    @GetMapping("/{bookId}")
    public ResponseEntity<BookJpa> getBookById(@PathVariable String bookId) {
        Optional<BookJpa> book = bookJpaService.getBookById(bookId);
        return book.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
    
    @GetMapping("/name/{bookName}")
    public ResponseEntity<BookJpa> getBookByName(@PathVariable String bookName) {
        Optional<BookJpa> book = bookJpaService.getBookByName(bookName);
        return book.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
    
    @PostMapping
    public ResponseEntity<BookJpa> createBook(@RequestBody BookJpa book) {
        if (bookJpaService.existsById(book.getBookId())) {
            return ResponseEntity.badRequest().build();
        }
        BookJpa savedBook = bookJpaService.createBook(book);
        return ResponseEntity.ok(savedBook);
    }
    
    @PutMapping("/{bookId}")
    public ResponseEntity<BookJpa> updateBook(@PathVariable String bookId, @RequestBody BookJpa book) {
        if (!bookJpaService.existsById(bookId)) {
            return ResponseEntity.notFound().build();
        }
        book.setBookId(bookId);
        BookJpa updatedBook = bookJpaService.updateBook(book);
        return ResponseEntity.ok(updatedBook);
    }
    
    @DeleteMapping("/{bookId}")
    public ResponseEntity<Void> deleteBook(@PathVariable String bookId) {
        if (!bookJpaService.existsById(bookId)) {
            return ResponseEntity.notFound().build();
        }
        bookJpaService.deleteBook(bookId);
        return ResponseEntity.noContent().build();
    }
    
    @GetMapping("/author/{author}")
    public List<BookJpa> getBooksByAuthor(@PathVariable String author) {
        return bookJpaService.getBooksByAuthor(author);
    }
    
    @GetMapping("/search/author")
    public List<BookJpa> searchBooksByAuthor(@RequestParam String author) {
        return bookJpaService.searchBooksByAuthor(author);
    }
    
    @GetMapping("/search/name")
    public List<BookJpa> searchBooksByName(@RequestParam String bookName) {
        return bookJpaService.searchBooksByName(bookName);
    }
    
    @GetMapping("/price/max/{maxPrice}")
    public List<BookJpa> getBooksByPriceRange(@PathVariable BigDecimal maxPrice) {
        return bookJpaService.getBooksByPriceRange(maxPrice);
    }
}