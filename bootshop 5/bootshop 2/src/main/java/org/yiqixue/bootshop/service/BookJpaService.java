package org.yiqixue.bootshop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yiqixue.bootshop.entity.BookJpa;
import org.yiqixue.bootshop.repository.BookJpaRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class BookJpaService {
    
    @Autowired
    private BookJpaRepository bookJpaRepository;
    
    public List<BookJpa> getAllBooks() {
        return bookJpaRepository.findAll();
    }
    
    public Optional<BookJpa> getBookById(String bookId) {
        return bookJpaRepository.findById(bookId);
    }
    
    public Optional<BookJpa> getBookByName(String bookName) {
        return bookJpaRepository.findByBookName(bookName);
    }
    
    public BookJpa createBook(BookJpa book) {
        return bookJpaRepository.save(book);
    }
    
    public BookJpa updateBook(BookJpa book) {
        return bookJpaRepository.save(book);
    }
    
    public void deleteBook(String bookId) {
        bookJpaRepository.deleteById(bookId);
    }
    
    public boolean existsById(String bookId) {
        return bookJpaRepository.existsById(bookId);
    }
    
    public List<BookJpa> getBooksByAuthor(String author) {
        return bookJpaRepository.findByAuthor(author);
    }
    
    public List<BookJpa> searchBooksByAuthor(String author) {
        return bookJpaRepository.findByAuthorContainingIgnoreCase(author);
    }
    
    public List<BookJpa> searchBooksByName(String bookName) {
        return bookJpaRepository.findByBookNameContainingIgnoreCase(bookName);
    }
    
    public List<BookJpa> getBooksByPriceRange(BigDecimal maxPrice) {
        return bookJpaRepository.findByPriceLessThanEqual(maxPrice);
    }
}