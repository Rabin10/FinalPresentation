package com.example.demo.Controller;

import com.example.demo.Entity.User;
import com.example.demo.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/all")
    public String getAllUsers(Model model) {
        model.addAttribute("userList", userService.getAllUsers());
        return "user-list";
    }

    @GetMapping("/ban/{userId}")
    public String banUser(@PathVariable int userId) {
        userService.banUser(userId);
        return "redirect:/users/all";
    }

    @GetMapping("/unban/{userId}")
    public String unbanUser(@PathVariable int userId) {
        userService.unbanUser(userId);
        return "redirect:/users/all";
    }
    @GetMapping("/createForm")
    public String showCreateUserForm(Model model) {
        model.addAttribute("user", new User()); // Add an empty User object to the model
        return "user-create"; // Return the name of the Thymeleaf template
    }
    @PostMapping("/create")
    public String createUser(@ModelAttribute User user) {
        userService.saveUser(user); // Save the user to the database
        return "redirect:/users/all"; // Redirect back to the user list

    }
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable int id, Model model) {
        User user = userService.getUserById(id);
        if (user == null) {
            throw new RuntimeException("User not found for ID: " + id);
        }
        model.addAttribute("user", user); // Add the user object to the model
        return "user-edit"; // Return the Thymeleaf template
    }

    // Handle the form submission for editing a user
    @PostMapping("/edit")
    public String editUser(@ModelAttribute User user) {
        userService.saveUser(user); // Save the updated user details
        return "redirect:/users/all"; // Redirect to the list of users
    }
    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable int id) {
        userService.deleteUserById(id); // Call the service to delete the user
        return "redirect:/users/all"; // Redirect back to the user management page
    }
}
