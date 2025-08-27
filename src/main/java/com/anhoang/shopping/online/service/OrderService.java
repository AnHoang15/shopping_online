package com.anhoang.shopping.online.service;

import com.anhoang.shopping.online.model.OrderRequest;
import com.anhoang.shopping.online.model.ProductOrder;
import jakarta.persistence.criteria.CriteriaBuilder;

import java.util.List;

public interface OrderService {
    public void saveOrder(Integer userId, OrderRequest orderRequest);
    public List<ProductOrder> getOrdersByUser(Integer userId);

    public Boolean updateOrderStatus(Integer id, String status);

    public List<ProductOrder> getAllOrders(Integer userId);

}
