package com.anhoang.shopping.online.repository;

import com.anhoang.shopping.online.model.Product;
import com.anhoang.shopping.online.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findByProduct(Product product);
}