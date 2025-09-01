package com.anhoang.shopping.online.controller;

import com.anhoang.shopping.online.model.Review;
import com.anhoang.shopping.online.model.UserDtls;
import com.anhoang.shopping.online.model.Product;
import com.anhoang.shopping.online.service.ReviewService;
import com.anhoang.shopping.online.service.UserService;
import com.anhoang.shopping.online.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
@RequestMapping("/review")
public class ReviewController {

    @Autowired private ReviewService reviewService;
    @Autowired private ProductService productService;
    @Autowired private UserService userService;

    @PostMapping("/add/{productId}")
    public String addReview(@PathVariable Integer productId,
                            @RequestParam int rating,
                            @RequestParam String comment,
                            Principal principal) {

        if (principal == null) return "redirect:/signin";

        Product product = productService.getProductById(productId);
        UserDtls user = userService.getUserByEmail(principal.getName());
        reviewService.addReview(product, user, rating, comment);

        return "redirect:/product/" + productId;   // quay lại trang chi tiết
    }

    @PostMapping("/delete/{reviewId}")
    public String deleteReview(@PathVariable Long reviewId, Principal principal) { // <-- Long
        if (principal == null) return "redirect:/signin";

        Review review = reviewService.getReviewById(reviewId);
        if (review == null) return "redirect:/";  // hoặc trang 404

        UserDtls currentUser = userService.getUserByEmail(principal.getName());
        boolean isOwner = review.getUser() != null
                && review.getUser().getId().equals(currentUser.getId());
        boolean isAdmin = "ROLE_ADMIN".equalsIgnoreCase(currentUser.getRole());

        if (isOwner || isAdmin) {
            reviewService.deleteReview(reviewId);
        }

        return "redirect:/product/" + review.getProduct().getId();
    }
}
