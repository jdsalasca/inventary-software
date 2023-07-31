package com.sena.inventarioback.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sena.inventarioback.models.Status;

@Repository
public interface StatusRepository extends JpaRepository<Status, Long> {
    // Add custom queries if needed
}
