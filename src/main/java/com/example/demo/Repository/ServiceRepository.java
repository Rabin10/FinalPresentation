package com.example.demo.Repository;

import com.example.demo.Entity.ServiceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServiceRepository extends JpaRepository<ServiceEntity, Integer> {

    // Find services by status (e.g., Pending, Approved, Rejected)
    List<ServiceEntity> findByStatus(String status);

    // Custom query to find services by name (partial match)
    @Query(value = "SELECT * FROM services s WHERE s.name LIKE %?1%", nativeQuery = true)
    List<ServiceEntity> findServicesByNameMatching(String name);
}
