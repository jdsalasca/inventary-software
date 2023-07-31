package com.sena.inventarioback.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sena.inventarioback.models.DigitalDocument;

@Repository
public interface DigitalDocumentRepository extends JpaRepository<DigitalDocument, Long> {
    // You can add custom queries here if needed
}
