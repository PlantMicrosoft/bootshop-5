package org.yiqixue.bootshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.yiqixue.bootshop.entity.Book;
import org.yiqixue.bootshop.service.BookJdbcService;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/jdbc/books")
public class BookJdbcController {
    
    @Autowired
    private BookJdbcService bookJdbcService;
    
    @GetMapping
    public List<Book> getAllBooks() {
        return bookJdbcService.getAllBooks();
    }
    
    @GetMapping("/{bookId}")
    public ResponseEntity<Book> getBookById(@PathVariable String bookId) {
        Optional<Book> book = bookJdbcService.getBookById(bookId);
        return book.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
    
    @GetMapping("/name/{bookName}")
    public ResponseEntity<Book> getBookByName(@PathVariable String bookName) {
        Optional<Book> book = bookJdbcService.getBookByName(bookName);
        return book.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
    
    @PostMapping
    public ResponseEntity<Book> createBook(@RequestBody Book book) {
        if (bookJdbcService.existsById(book.getBookId())) {
            return ResponseEntity.badRequest().build();
        }
        Book savedBook = bookJdbcService.saveBook(book);
        return ResponseEntity.ok(savedBook);
    }
    
    @PutMapping("/{bookId}")
    public ResponseEntity<Book> updateBook(@PathVariable String bookId, @RequestBody Book book) {
        if (!bookJdbcService.existsById(bookId)) {
            return ResponseEntity.notFound().build();
        }
        book.setBookId(bookId);
        Book updatedBook = bookJdbcService.saveBook(book);
        return ResponseEntity.ok(updatedBook);
    }
    
    @DeleteMapping("/{bookId}")
    public ResponseEntity<Void> deleteBook(@PathVariable String bookId) {
        if (!bookJdbcService.existsById(bookId)) {
            return ResponseEntity.notFound().build();
        }
        bookJdbcService.deleteBook(bookId);
        return ResponseEntity.noContent().build();
    }
    
    @GetMapping("/author/{author}")
    public List<Book> getBooksByAuthor(@PathVariable String author) {
        return bookJdbcService.getBooksByAuthor(author);
    }
    
    @GetMapping("/search/author")
    public List<Book> searchBooksByAuthor(@RequestParam String author) {
        return bookJdbcService.searchBooksByAuthor(author);
    }
    
    @GetMapping("/search/name")
    public List<Book> searchBooksByName(@RequestParam String bookName) {
        return bookJdbcService.searchBooksByName(bookName);
    }
    
    @GetMapping("/price/max/{maxPrice}")
    public List<Book> getBooksByPriceRange(@PathVariable BigDecimal maxPrice) {
        return bookJdbcService.getBooksByPriceRange(maxPrice);
    }
}