package org.yiqixue.bootshop.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.math.BigDecimal;

@Table("tbbook")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Book implements Persistable<String> {
    
    @Id
    @Column("bookId")
    private String bookId;
    
    @Column("bookName")
    private String bookName;
    
    @Column("author")
    private String author;
    
    @Column("isbn")
    private String isbn;
    
    @Column("publisher")
    private String publisher;
    
    @Column("price")
    private BigDecimal price;
    
    @Column("discount")
    private Double discount;
    
    @Column("edition")
    private String edition;
    
    @Column("imageFile")
    private String imageFile;
    
    @Transient
    private boolean isNew = true;
    
    @Override
    public String getId() {
        return bookId;
    }
    
    @Override
    public boolean isNew() {
        return isNew;
    }
    
    public void setNew(boolean isNew) {
        this.isNew = isNew;
    }
}
