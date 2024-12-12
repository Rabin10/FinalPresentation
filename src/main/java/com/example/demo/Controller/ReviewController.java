package com.example.demo.Controller;

import com.example.demo.Entity.Review;
import com.example.demo.Entity.ServiceEntity;
import com.example.demo.Service.ReviewService;
import com.example.demo.Service.ServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/reviews")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @Autowired
    private ServiceService serviceService;

    @GetMapping("/all")
    public String getAllReviews(Model model) {
        model.addAttribute("reviewList", reviewService.getAllReviews());
        return "review-list";
    }

    @GetMapping("/createForm")
    public String showCreateForm(Model model) {
        model.addAttribute("review", new Review());
        model.addAttribute("services", serviceService.getAllServices());
        return "review-create";
    }

    @PostMapping("/create")
    public String createReview(@ModelAttribute Review review) {
        ServiceEntity service = serviceService.getServiceById(review.getService().getServiceId());
        review.setService(service);
        reviewService.saveReview(review);
        return "redirect:/reviews/all";
    }

    @GetMapping("/update/{reviewId}")
    public String showUpdateForm(@PathVariable int reviewId, Model model) {
        model.addAttribute("review", reviewService.getReviewById(reviewId));
        model.addAttribute("services", serviceService.getAllServices());
        return "review-update";
    }

    @PostMapping("/update")
    public String updateReview(@ModelAttribute Review review) {
        ServiceEntity service = serviceService.getServiceById(review.getService().getServiceId());
        review.setService(service);
        reviewService.saveReview(review);
        return "redirect:/reviews/all";
    }

    @GetMapping("/delete/{reviewId}")
    public String deleteReview(@PathVariable int reviewId) {
        reviewService.deleteReviewById(reviewId);
        return "redirect:/reviews/all";
    }
}
