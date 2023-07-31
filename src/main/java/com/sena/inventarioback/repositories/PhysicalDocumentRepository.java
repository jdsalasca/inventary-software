package com.sena.inventarioback.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sena.inventarioback.models.PhysicalDocument;

@Repository
public interface PhysicalDocumentRepository extends JpaRepository<PhysicalDocument, Long> {
    // Add custom queries if needed
}
