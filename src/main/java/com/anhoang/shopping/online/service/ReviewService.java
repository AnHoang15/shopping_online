package com.anhoang.shopping.online.service;

import com.anhoang.shopping.online.model.Review;
import com.anhoang.shopping.online.model.Product;
import com.anhoang.shopping.online.model.UserDtls;

import java.util.List;

public interface ReviewService {
    Review addReview(Product product, UserDtls user, int rating, String comment);
    List<Review> getReviewsByProduct(Product product);
    void deleteReview(Integer reviewId);

    Review getReviewById(Integer reviewId);
}
