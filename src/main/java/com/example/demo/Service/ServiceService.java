package com.example.demo.Service;

import com.example.demo.Entity.ServiceEntity;
import com.example.demo.Repository.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceService {

    @Autowired
    private ServiceRepository serviceRepository;
    public long getTotalServices() {
        return serviceRepository.count();
    }

    // Get all services
    public List<ServiceEntity> getAllServices() {
        return serviceRepository.findAll();
    }

    // Get a single service by ID
    public ServiceEntity getServiceById(int serviceId) {
        return serviceRepository.findById(serviceId).orElse(null);
    }

    // Save or update a service
    public void saveService(ServiceEntity service) {
        serviceRepository.save(service);
    }

    // Delete a service by ID
    public void deleteServiceById(int serviceId) {
        serviceRepository.deleteById(serviceId);
    }

    // Search for services by name (partial match)
    public List<ServiceEntity> getServicesByName(String name) {
        return serviceRepository.findServicesByNameMatching(name);
    }
}
