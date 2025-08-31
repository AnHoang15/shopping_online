package com.anhoang.shopping.online.repository;

import com.anhoang.shopping.online.model.Review;
import com.anhoang.shopping.online.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ReviewRespository extends JpaRepository<Review, Integer> {
    List<Review> findByProduct(Product product);

}
