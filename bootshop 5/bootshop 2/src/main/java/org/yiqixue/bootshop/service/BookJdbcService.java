package org.yiqixue.bootshop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yiqixue.bootshop.entity.Book;
import org.yiqixue.bootshop.repository.BookJdbcRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class BookJdbcService {
    
    @Autowired
    private BookJdbcRepository bookJdbcRepository;
    
    public List<Book> getAllBooks() {
        return bookJdbcRepository.findAll();
    }
    
    public Optional<Book> getBookById(String bookId) {
        return bookJdbcRepository.findById(bookId);
    }
    
    public Optional<Book> getBookByName(String bookName) {
        return bookJdbcRepository.findByBookName(bookName);
    }
    
    public Book saveBook(Book book) {
        return bookJdbcRepository.save(book);
    }
    
    public void deleteBook(String bookId) {
        bookJdbcRepository.deleteById(bookId);
    }
    
    public boolean existsById(String bookId) {
        return bookJdbcRepository.existsById(bookId);
    }
    
    public List<Book> getBooksByAuthor(String author) {
        return bookJdbcRepository.findByAuthor(author);
    }
    
    public List<Book> searchBooksByAuthor(String author) {
        return bookJdbcRepository.findByAuthorContainingIgnoreCase(author);
    }
    
    public List<Book> searchBooksByName(String bookName) {
        return bookJdbcRepository.findByBookNameContainingIgnoreCase(bookName);
    }
    
    public List<Book> getBooksByPriceRange(BigDecimal maxPrice) {
        return bookJdbcRepository.findByPriceLessThanEqual(maxPrice);
    }
}