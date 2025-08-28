package com.anhoang.shopping.online.respository;

import com.anhoang.shopping.online.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.parameters.P;

import java.util.List;

public interface ProductResposity extends JpaRepository<Product, Integer> {
    List<Product> findByIsActiveTrue();

    List<Product> findByCategory(String category);

    List<Product> findByTitleContainingIgnoreCaseOrCategoryContainingIgnoreCase(String ch, String ch2);
}
