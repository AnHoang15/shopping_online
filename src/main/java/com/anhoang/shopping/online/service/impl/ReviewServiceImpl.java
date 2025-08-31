package com.anhoang.shopping.online.service.impl;

import com.anhoang.shopping.online.model.Product;
import com.anhoang.shopping.online.model.Review;
import com.anhoang.shopping.online.model.UserDtls;
import com.anhoang.shopping.online.repository.ReviewRespository;
import com.anhoang.shopping.online.repository.ReviewRespository;
import com.anhoang.shopping.online.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    private ReviewRespository reviewRepository;

    @Override
    public Review addReview(Product product, UserDtls user, int rating, String comment) {
        Review review = new Review();
        review.setProduct(product);
        review.setUser(user);
        review.setRating(rating);
        review.setComment(comment);
        return reviewRepository.save(review);
    }

    @Override
    public List<Review> getReviewsByProduct(Product product) {
        return reviewRepository.findByProduct(product);
    }


    @Override
    public void deleteReview(Integer reviewId) {
        reviewRepository.deleteById(reviewId);
    }

    @Override
    public Review getReviewById(Integer reviewId) {
        return reviewRepository.findById(reviewId)
                .orElse(null);
    }

}
