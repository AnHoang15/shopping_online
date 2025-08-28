package com.anhoang.shopping.online.service;

import com.anhoang.shopping.online.model.OrderRequest;
import com.anhoang.shopping.online.model.ProductOrder;

import java.util.List;

public interface OrderService {
    public void saveOrder(Integer userId, OrderRequest orderRequest) throws Exception;

    public List<ProductOrder> getOrdersByUser(Integer userId);

    public ProductOrder updateOrderStatus(Integer id, String status);

    public List<ProductOrder> getAllOrders();

    public ProductOrder getOrdersByOrderId(String orderId);

}
