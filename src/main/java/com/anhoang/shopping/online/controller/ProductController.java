package com.anhoang.shopping.online.controller;

import com.anhoang.shopping.online.model.Product;
import com.anhoang.shopping.online.model.Review;
import com.anhoang.shopping.online.model.UserDtls;
import com.anhoang.shopping.online.service.ProductService;
import com.anhoang.shopping.online.service.ReviewService;
import com.anhoang.shopping.online.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.security.Principal;
import java.util.List;

@Controller
public class ProductController {

    @Autowired private ProductService productService;
    @Autowired private ReviewService reviewService;
    @Autowired private UserService userService;

    @GetMapping("/product/{id}")
    public String viewProduct(@PathVariable Integer id, Model model, Principal principal) {
        Product product = productService.getProductById(id);
        List<Review> reviews = reviewService.getReviewsByProduct(product);

        double avgRating = reviews.stream()
                .mapToInt(Review::getRating)
                .average()
                .orElse(0.0);

        model.addAttribute("product", product);
        model.addAttribute("reviews", reviews);             // <-- tên trùng với view
        model.addAttribute("avgRating", avgRating);
        model.addAttribute("reviewCount", reviews.size());

        if (principal != null) {
            UserDtls user = userService.getUserByEmail(principal.getName());
            model.addAttribute("user", user);
        }

        return "view_product"; // trỏ tới view_product.html
    }
}
