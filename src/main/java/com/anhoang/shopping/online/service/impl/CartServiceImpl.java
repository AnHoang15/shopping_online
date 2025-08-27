package com.anhoang.shopping.online.service.impl;

import com.anhoang.shopping.online.model.Cart;
import com.anhoang.shopping.online.model.Product;
import com.anhoang.shopping.online.model.UserDtls;
import com.anhoang.shopping.online.respository.CartRespository;
import com.anhoang.shopping.online.respository.ProductResposity;
import com.anhoang.shopping.online.respository.UserRespository;
import com.anhoang.shopping.online.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartRespository cartRespository;

    @Autowired
    private UserRespository userRespository;

    @Autowired
    private ProductResposity productResposity;

    @Override
    public Cart saveCart(Integer productId, Integer userId) {
        UserDtls user = userRespository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found: " + userId));
        Product product = productResposity.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found: " + productId));

        Double discountPrice = product.getDiscountPrice();
        if (discountPrice == null) {
            discountPrice = product.getPrice(); // fallback
        }

        Cart cart = cartRespository.findByProductIdAndUserId(productId, userId);

        if (cart == null) {
            cart = new Cart();
            cart.setProduct(product);
            cart.setUser(user);
            cart.setQuantity(1);
        } else {
            cart.setQuantity(cart.getQuantity() + 1);
        }

        cart.setTotalPrice(cart.getQuantity() * discountPrice);

        return cartRespository.save(cart);
    }



    @Override
    public List<Cart> getCartsByUser(Integer userId) {
        List<Cart> carts = cartRespository.findByUserId(userId);
        Double totalOrderPrice = 0.0;
        List<Cart> updateCarts = new ArrayList<>();

        for (Cart c : carts) {
            // Lấy discountPrice, nếu null thì fallback về price
            Double discountPrice = c.getProduct().getDiscountPrice();
            if (discountPrice == null) {
                discountPrice = c.getProduct().getPrice(); // fallback về giá gốc
            }

            // Tính tổng giá cho từng sản phẩm
            Double totalPrice = discountPrice * c.getQuantity();
            c.setTotalPrice(totalPrice);

            // Cộng dồn vào tổng đơn hàng
            totalOrderPrice += totalPrice;

            // Gán tổng đơn hàng vào cart (nếu bạn cần show ở Thymeleaf)
            c.setTotalOrderPrice(totalOrderPrice);

            updateCarts.add(c);
        }

        return updateCarts;
    }


    @Override
    public Integer getCountCart(Integer userId) {
        Integer countByUserId = cartRespository.countByUserId(userId);
        return countByUserId;
    }

    @Override
    public void updateQuantity(String sy, Integer cid) {
        Cart cart = cartRespository.findById(cid).get();
        int updateQuantity;
        if(sy.equalsIgnoreCase("de")) {
            updateQuantity = cart.getQuantity() -1;
            if(updateQuantity <= 0) {
                cartRespository.delete(cart);
            } else {
                cart.setQuantity(updateQuantity);
                cartRespository.save(cart);
            }
        } else {
            updateQuantity = cart.getQuantity() + 1;
            cart.setQuantity(updateQuantity);
            cartRespository.save(cart);
        }


    }
}
