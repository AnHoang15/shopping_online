package com.anhoang.shopping.online.service.impl;

import com.anhoang.shopping.online.model.Cart;
import com.anhoang.shopping.online.model.OrderAddress;
import com.anhoang.shopping.online.model.ProductOrder;
import com.anhoang.shopping.online.respository.CartRespository;
import com.anhoang.shopping.online.respository.ProductOrderRespository;
import com.anhoang.shopping.online.service.OrderService;
import com.anhoang.shopping.online.util.CommonUtil;
import com.anhoang.shopping.online.util.OrderStatus;
import com.anhoang.shopping.online.model.OrderRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private ProductOrderRespository orderRespository;

    @Autowired
    private CartRespository cartRespository;

    @Autowired
	private CommonUtil commonUtil;

    @Override
    public void saveOrder(Integer userId, OrderRequest orderRequest) throws Exception {
        List< Cart> carts = cartRespository.findByUserId(userId);
        for(Cart cart : carts) {
            ProductOrder order = new ProductOrder();
            order.setOrderId(UUID.randomUUID().toString());
            order.setOrderDate(LocalDate.now());
            order.setProduct(cart.getProduct());
            order.setPrice(cart.getProduct().getDiscountPrice());
            order.setQuantity(cart.getQuantity());
            order.setUser(cart.getUser());
            order.setStatus(OrderStatus.IN_PROGRESS.getName());
            order.setPaymentType(orderRequest.getPaymentType());
            OrderAddress address = new OrderAddress();
            address.setFirstName(orderRequest.getFirstName());
            address.setLastName(orderRequest.getLastName());
            address.setEmail(orderRequest.getEmail());
            address.setMobileNo(orderRequest.getMobileNo());
            address.setAddress(orderRequest.getAddress());
            address.setCity(orderRequest.getCity());
            address.setState(orderRequest.getState());
            address.setPincode(orderRequest.getPincode());

            order.setOrderAddress(address);

            ProductOrder saveOrder = orderRespository.save(order);
			commonUtil.sendMailForProductOrder(saveOrder, "success");
        }

    }

    @Override
    public List<ProductOrder> getOrdersByUser(Integer userId) {
        List<ProductOrder> orders = orderRespository.findByUserId(userId);
        return orders;
    }

    @Override
	public ProductOrder updateOrderStatus(Integer id, String status) {
		Optional<ProductOrder> findById = orderRespository.findById(id);
		if (findById.isPresent()) {
			ProductOrder productOrder = findById.get();
			productOrder.setStatus(status);
			ProductOrder updateOrder = orderRespository.save(productOrder);
			return updateOrder;
		}
		return null;
	}

    @Override
    public List<ProductOrder> getAllOrders() {
        return orderRespository.findAll();
    }
}
