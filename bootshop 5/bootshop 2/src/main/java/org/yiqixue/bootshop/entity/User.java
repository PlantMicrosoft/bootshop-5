package org.yiqixue.bootshop.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Table(name = "tbuser")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userid")
    private Integer userId;
    
    @Column(name = "username", length = 20)
    private String username;
    
    @Column(name = "password", length = 50)
    private String password;
    
    @Column(name = "Email", length = 50)
    private String email;
    
    @Column(name = "Phone", length = 30)
    private String phone;
}