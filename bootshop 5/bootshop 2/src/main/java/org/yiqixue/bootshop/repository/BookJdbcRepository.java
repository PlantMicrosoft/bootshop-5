package org.yiqixue.bootshop.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.yiqixue.bootshop.entity.Book;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository
public class BookJdbcRepository {
    
    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    private final RowMapper<Book> bookRowMapper = new RowMapper<Book>() {
        @Override
        public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
            Book book = new Book();
            book.setBookId(rs.getString("bookId"));
            book.setBookName(rs.getString("bookName"));
            book.setAuthor(rs.getString("author"));
            book.setIsbn(rs.getString("isbn"));
            book.setPublisher(rs.getString("publisher"));
            book.setPrice(rs.getBigDecimal("price"));
            book.setDiscount(rs.getDouble("discount"));
            book.setEdition(rs.getString("edition"));
            book.setImageFile(rs.getString("imageFile"));
            return book;
        }
    };
    
    public Book save(Book book) {
        String sql = "INSERT INTO tbbook (bookId, bookName, author, isbn, publisher, price, discount, edition, imageFile) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, book.getBookId(), book.getBookName(), book.getAuthor(), 
                           book.getIsbn(), book.getPublisher(), book.getPrice(), 
                           book.getDiscount(), book.getEdition(), book.getImageFile());
        return book;
    }
    
    public Optional<Book> findById(String bookId) {
        String sql = "SELECT * FROM tbbook WHERE bookId = ?";
        List<Book> books = jdbcTemplate.query(sql, bookRowMapper, bookId);
        return books.isEmpty() ? Optional.empty() : Optional.of(books.get(0));
    }
    
    public List<Book> findAll() {
        String sql = "SELECT * FROM tbbook";
        return jdbcTemplate.query(sql, bookRowMapper);
    }
    
    public void deleteById(String bookId) {
        String sql = "DELETE FROM tbbook WHERE bookId = ?";
        jdbcTemplate.update(sql, bookId);
    }
    
    public boolean existsById(String bookId) {
        String sql = "SELECT COUNT(*) FROM tbbook WHERE bookId = ?";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, bookId);
        return count != null && count > 0;
    }
    
    public Optional<Book> findByBookName(String bookName) {
        String sql = "SELECT * FROM tbbook WHERE bookName = ?";
        List<Book> books = jdbcTemplate.query(sql, bookRowMapper, bookName);
        return books.isEmpty() ? Optional.empty() : Optional.of(books.get(0));
    }
    
    public List<Book> findByAuthor(String author) {
        String sql = "SELECT * FROM tbbook WHERE author = ?";
        return jdbcTemplate.query(sql, bookRowMapper, author);
    }
    
    public Optional<Book> findByIsbn(String isbn) {
        String sql = "SELECT * FROM tbbook WHERE isbn = ?";
        List<Book> books = jdbcTemplate.query(sql, bookRowMapper, isbn);
        return books.isEmpty() ? Optional.empty() : Optional.of(books.get(0));
    }
    
    public List<Book> findByPublisher(String publisher) {
        String sql = "SELECT * FROM tbbook WHERE publisher = ?";
        return jdbcTemplate.query(sql, bookRowMapper, publisher);
    }
    
    public List<Book> findByPriceLessThanEqual(BigDecimal price) {
        String sql = "SELECT * FROM tbbook WHERE price <= ?";
        return jdbcTemplate.query(sql, bookRowMapper, price);
    }
    
    public List<Book> findByAuthorContainingIgnoreCase(String author) {
        String sql = "SELECT * FROM tbbook WHERE LOWER(author) LIKE LOWER(?)";
        return jdbcTemplate.query(sql, bookRowMapper, "%" + author + "%");
    }
    
    public List<Book> findByBookNameContainingIgnoreCase(String bookName) {
        String sql = "SELECT * FROM tbbook WHERE LOWER(bookName) LIKE LOWER(?)";
        return jdbcTemplate.query(sql, bookRowMapper, "%" + bookName + "%");
    }
    
    public boolean existsByIsbn(String isbn) {
        String sql = "SELECT COUNT(*) FROM tbbook WHERE isbn = ?";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, isbn);
        return count != null && count > 0;
    }
}