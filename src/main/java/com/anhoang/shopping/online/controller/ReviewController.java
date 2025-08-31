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
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/review")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @Autowired
    private ProductService productService;

    @Autowired
    private UserService userService;

    /**
     * Thêm đánh giá mới cho sản phẩm
     */
    @PostMapping("/add/{productId}")
    public String addReview(@PathVariable Integer productId,
                            @RequestParam int rating,
                            @RequestParam String comment,
                            Principal principal) {
        Product product = productService.getProductById(productId);
        UserDtls user = userService.getUserByEmail(principal.getName());

        reviewService.addReview(product, user, rating, comment);

        return "redirect:/product/" + productId;// quay lại trang chi tiết sản phẩm
    }


    /**
     * Xem danh sách review của 1 sản phẩm (có thể gọi từ Ajax hoặc hiển thị trên view_product.html)
     */
    @GetMapping("/product/{productId}")
    public String viewProduct(@PathVariable Integer productId, Model model, Principal principal) {
        Product product = productService.getProductById(productId);
        List<Review> reviews = reviewService.getReviewsByProduct(product);

        // Tính trung bình rating (bỏ qua 0)
        Double avgRating = reviews.stream()
                .filter(r -> r.getRating() > 0) // chỉ lấy review có rating hợp lệ
                .mapToInt(Review::getRating)
                .average()
                .orElse(0.0);

        int reviewCount = reviews.size();

// Debug
        //System.out.println("DEBUG: avgRating=" + avgRating + ", reviewCount=" + reviewCount);


        // Truyền sang view
        model.addAttribute("product", product);
        model.addAttribute("reviewsList", reviews);
        model.addAttribute("avgRating", avgRating);
        model.addAttribute("reviewCount", reviewCount);
        model.asMap().forEach((k,v) -> System.out.println("MODEL >> " + k + "=" + v));



        return "user/view_product";
    }


    /**
     * Xóa review (chỉ cho chủ review hoặc admin)
     */
    @PostMapping("/delete/{reviewId}")
    public String deleteReview(@PathVariable Integer reviewId, Principal principal) {
        Review review = reviewService.getReviewById(reviewId);
        UserDtls currentUser = userService.getUserByEmail(principal.getName());

        if (review.getUser().getId().equals(currentUser.getId())
                || "ROLE_ADMIN".equalsIgnoreCase(currentUser.getRole())) {
            reviewService.deleteReview(reviewId);
        }

        // redirect về sản phẩm chứa review này
        return "redirect:/product/" + review.getProduct().getId();
    }


}
