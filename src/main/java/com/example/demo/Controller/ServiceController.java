package com.example.demo.Controller;

import com.example.demo.Entity.ServiceEntity;
import com.example.demo.Service.ServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/services")
public class ServiceController {

    @Autowired
    private ServiceService serviceService;

    // Fetch all services
    @GetMapping("/all")
    public String getAllServices(Model model) {
        model.addAttribute("serviceList", serviceService.getAllServices());
        return "service-list"; // Connects to service-list.html template
    }

    // Display the form to create a new service
    @GetMapping("/createForm")
    public String showCreateForm(Model model) {
        model.addAttribute("service", new ServiceEntity());
        return "service-create"; // Ensure service-create.html exists
    }

    // Handle the creation of a new service
    @PostMapping("/create")
    public String createService(@ModelAttribute ServiceEntity service) {
        serviceService.saveService(service);
        return "redirect:/services/all";
    }

    // Display the form to update an existing service
    @GetMapping("/update/{serviceId}")
    public String showUpdateForm(@PathVariable int serviceId, Model model) {
        ServiceEntity service = serviceService.getServiceById(serviceId);
        if (service == null) {
            throw new RuntimeException("Service not found for ID: " + serviceId);
        }
        model.addAttribute("service", service);
        return "service-update"; // Connects to service-update.html template
    }

    // Handle the submission of updated service data
    @PostMapping("/update")
    public String updateService(@ModelAttribute ServiceEntity service) {
        serviceService.saveService(service);
        return "redirect:/services/all";
    }

    // Delete a service by ID
    @GetMapping("/delete/{serviceId}")
    public String deleteService(@PathVariable int serviceId) {
        serviceService.deleteServiceById(serviceId);
        return "redirect:/services/all";
    }


}
