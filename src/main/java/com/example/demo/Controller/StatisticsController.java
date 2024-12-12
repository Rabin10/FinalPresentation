package com.example.demo.Controller;

import com.example.demo.Service.UserService;
import com.example.demo.Service.ServiceService;
import com.example.demo.Service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/statistics")
public class StatisticsController {

    @Autowired
    private UserService userService;

    @Autowired
    private ServiceService serviceService;

    @Autowired
    private ReviewService reviewService;

    @GetMapping("")
    public String viewStatistics(Model model) {
        System.out.println("Fetching statistics...");
        model.addAttribute("totalUsers", userService.getTotalUsers());
        model.addAttribute("totalProviders", userService.getTotalProviders());
        model.addAttribute("totalCustomers", userService.getTotalCustomers());
        model.addAttribute("totalServices", serviceService.getTotalServices());
        model.addAttribute("totalReviews", reviewService.getTotalReviews());
        System.out.println("Statistics fetched successfully.");
        return "statistics";
    }
}
