package org.yiqixue.bootshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.yiqixue.bootshop.entity.BookJpa;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface BookJpaRepository extends JpaRepository<BookJpa, String> {
    
    Optional<BookJpa> findByBookName(String bookName);
    
    List<BookJpa> findByAuthor(String author);
    
    Optional<BookJpa> findByIsbn(String isbn);
    
    List<BookJpa> findByPublisher(String publisher);
    
    List<BookJpa> findByPriceLessThanEqual(BigDecimal price);
    
    List<BookJpa> findByAuthorContainingIgnoreCase(String author);
    
    List<BookJpa> findByBookNameContainingIgnoreCase(String bookName);
    
    boolean existsByIsbn(String isbn);
}