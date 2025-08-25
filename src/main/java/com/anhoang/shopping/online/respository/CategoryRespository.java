package com.anhoang.shopping.online.respository;

import com.anhoang.shopping.online.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRespository extends JpaRepository<Category, Integer> {

    public Boolean existsByName(String name);

    public List<Category> findByIsActiveTrue();
}
