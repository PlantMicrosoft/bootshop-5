package org.yiqixue.bootshop.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.yiqixue.bootshop.entity.Book;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends CrudRepository<Book, String> {
    
    Optional<Book> findByBookName(String bookName);
    
    List<Book> findByAuthor(String author);
    
    Optional<Book> findByIsbn(String isbn);
    
    List<Book> findByPublisher(String publisher);
    
    List<Book> findByPriceLessThanEqual(BigDecimal price);
    
    List<Book> findByAuthorContainingIgnoreCase(String author);
    
    List<Book> findByBookNameContainingIgnoreCase(String bookName);
    
    boolean existsByIsbn(String isbn);
}