package org.yiqixue.bootshop.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "tbbook")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookJpa {
    
    @Id
    @Column(name = "bookId", length = 15, nullable = false, insertable = true, updatable = true)
    private String bookId;
    
    @Column(name = "bookName", length = 100)
    private String bookName;
    
    @Column(name = "author", length = 50)
    private String author;
    
    @Column(name = "isbn", length = 20)
    private String isbn;
    
    @Column(name = "publisher", length = 20)
    private String publisher;
    
    @Column(name = "price", precision = 10, scale = 2)
    private BigDecimal price;
    
    @Column(name = "discount")
    private Double discount;
    
    @Column(name = "edition", length = 10)
    private String edition;
    
    @Column(name = "imageFile", length = 20)
    private String imageFile;
}