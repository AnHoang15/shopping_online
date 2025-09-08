package com.anhoang.shopping.online.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
//@NoArgsConstructor
@Getter
@Setter
@Entity
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int rating; // từ 1-5 sao
    private String comment;
    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserDtls user;  // ai viết review

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;  // review cho sản phẩm nào

    // getters & setters
    public Review() {
        this.createdAt = LocalDateTime.now();
    }

    // getter setter ...
}
