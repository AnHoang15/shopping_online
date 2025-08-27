package com.anhoang.shopping.online.respository;

import com.anhoang.shopping.online.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartRespository extends JpaRepository<Cart, Integer> {

    public Cart findByProductIdAndUserId(Integer productId, Integer userId);
    public Integer countByUserId(Integer userId);

    public List<Cart> findByUserId(Integer userId);
}
