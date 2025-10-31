package org.yiqixue.bootshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.yiqixue.bootshop.entity.Book;
import org.yiqixue.bootshop.service.BookService;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/books")
public class BookController {
    
    @Autowired
    private BookService bookService;
    
    @GetMapping
    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }
    
    @GetMapping("/{bookId}")
    public ResponseEntity<Book> getBookById(@PathVariable String bookId) {
        Optional<Book> book = bookService.getBookById(bookId);
        return book.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
    
    @GetMapping("/name/{bookName}")
    public ResponseEntity<Book> getBookByName(@PathVariable String bookName) {
        Optional<Book> book = bookService.getBookByName(bookName);
        return book.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
    
    @PostMapping
    public ResponseEntity<Book> createBook(@RequestBody Book book) {
        if (bookService.existsById(book.getBookId())) {
            return ResponseEntity.badRequest().build();
        }
        Book savedBook = bookService.createBook(book);
        return ResponseEntity.ok(savedBook);
    }
    
    @PutMapping("/{bookId}")
    public ResponseEntity<Book> updateBook(@PathVariable String bookId, @RequestBody Book book) {
        if (!bookService.existsById(bookId)) {
            return ResponseEntity.notFound().build();
        }
        book.setBookId(bookId);
        Book updatedBook = bookService.updateBook(book);
        return ResponseEntity.ok(updatedBook);
    }
    
    @DeleteMapping("/{bookId}")
    public ResponseEntity<Void> deleteBook(@PathVariable String bookId) {
        if (!bookService.existsById(bookId)) {
            return ResponseEntity.notFound().build();
        }
        bookService.deleteBook(bookId);
        return ResponseEntity.noContent().build();
    }
    
    @GetMapping("/author/{author}")
    public List<Book> getBooksByAuthor(@PathVariable String author) {
        return bookService.getBooksByAuthor(author);
    }
    
    @GetMapping("/search/author")
    public List<Book> searchBooksByAuthor(@RequestParam String author) {
        return bookService.searchBooksByAuthor(author);
    }
    
    @GetMapping("/search/name")
    public List<Book> searchBooksByName(@RequestParam String bookName) {
        return bookService.searchBooksByName(bookName);
    }
    
    @GetMapping("/price/max/{maxPrice}")
    public List<Book> getBooksByPriceRange(@PathVariable BigDecimal maxPrice) {
        return bookService.getBooksByPriceRange(maxPrice);
    }
}