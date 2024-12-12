package com.example.demo.Service;

import com.example.demo.Repository.UserRepository;
import com.example.demo.Repository.ServiceRepository;
import com.example.demo.Repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StatisticsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ServiceRepository serviceRepository;

    @Autowired
    private ReviewRepository reviewRepository;

    public long getTotalUsers() {
        return userRepository.count();
    }

    public long getTotalServices() {
        return serviceRepository.count();
    }

    public long getTotalReviews() {
        return reviewRepository.count();
    }
}
