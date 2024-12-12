package com.example.demo.Entity;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "services")
public class ServiceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int serviceId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String status; // Possible values: "Pending", "Approved", "Rejected"

    @Column(nullable = false)
    private String description;

    // One-to-Many relationship with Review
    @OneToMany(mappedBy = "service", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Review> reviews = new ArrayList<>();

    // Default Constructor
    public ServiceEntity() {}

    // Parameterized Constructor
    public ServiceEntity(String name, String status, String description) {
        this.name = name;
        this.status = status;
        this.description = description;
    }

    // Getter and Setter for serviceId
    public int getServiceId() {
        return serviceId;
    }

    public void setServiceId(int serviceId) {
        this.serviceId = serviceId;
    }

    // Getter and Setter for name
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // Getter and Setter for status
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    // Getter and Setter for description
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    // Getter and Setter for reviews
    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    // Add a convenience method to add a review
    public void addReview(Review review) {
        reviews.add(review);
        review.setService(this); // Set the relationship on the other side
    }

    // Add a convenience method to remove a review
    public void removeReview(Review review) {
        reviews.remove(review);
        review.setService(null); // Break the relationship on the other side
    }
}
