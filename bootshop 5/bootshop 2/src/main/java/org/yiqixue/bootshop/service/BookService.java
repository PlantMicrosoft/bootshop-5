package org.yiqixue.bootshop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yiqixue.bootshop.entity.Book;
import org.yiqixue.bootshop.repository.BookRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    
    @Autowired
    private BookRepository bookRepository;
    
    public List<Book> getAllBooks() {
        return (List<Book>) bookRepository.findAll();
    }
    
    public Optional<Book> getBookById(String bookId) {
        Optional<Book> book = bookRepository.findById(bookId);
        book.ifPresent(b -> b.setNew(false));
        return book;
    }
    
    public Optional<Book> getBookByName(String bookName) {
        return bookRepository.findByBookName(bookName);
    }
    
    public Book createBook(Book book) {
        book.setNew(true);
        return bookRepository.save(book);
    }
    
    public Book updateBook(Book book) {
        book.setNew(false);
        return bookRepository.save(book);
    }
    
    public void deleteBook(String bookId) {
        bookRepository.deleteById(bookId);
    }
    
    public boolean existsById(String bookId) {
        return bookRepository.existsById(bookId);
    }
    
    public List<Book> getBooksByAuthor(String author) {
        return bookRepository.findByAuthor(author);
    }
    
    public List<Book> searchBooksByAuthor(String author) {
        return bookRepository.findByAuthorContainingIgnoreCase(author);
    }
    
    public List<Book> searchBooksByName(String bookName) {
        return bookRepository.findByBookNameContainingIgnoreCase(bookName);
    }
    
    public List<Book> getBooksByPriceRange(BigDecimal maxPrice) {
        return bookRepository.findByPriceLessThanEqual(maxPrice);
    }
}