package com.example.demo.Service;

import com.example.demo.Entity.User;
import com.example.demo.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {


    @Autowired
    private UserRepository userRepository;
    public long getTotalUsers() {
        return userRepository.count();
    }

    // Get the total number of providers
    public long getTotalProviders() {
        return userRepository.countByRole("Provider");
    }

    // Get the total number of customers
    public long getTotalCustomers() {
        return userRepository.countByRole("Customer");
    }


    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(int userId) {
        return userRepository.findById(userId).orElse(null);
    }

    public void saveUser(User user) {
        userRepository.save(user);
    }

    public void deleteUserById(int userId) {
        userRepository.deleteById(userId);
    }

    public void banUser(int userId) {
        User user = getUserById(userId);
        if (user != null) {
            user.setStatus("Banned");
            saveUser(user);
        }
    }

    public void unbanUser(int userId) {
        User user = getUserById(userId);
        if (user != null) {
            user.setStatus("Active");
            saveUser(user);
        }

    }

}
