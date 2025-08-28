package com.anhoang.shopping.online.respository;

import com.anhoang.shopping.online.model.ProductOrder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductOrderRespository extends JpaRepository<ProductOrder, Integer> {

    List<ProductOrder> findByUserId(Integer userId);

    ProductOrder findByOrderId(String orderId);
}
