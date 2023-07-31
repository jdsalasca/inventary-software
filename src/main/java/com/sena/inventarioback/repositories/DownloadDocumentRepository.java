package com.sena.inventarioback.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sena.inventarioback.models.DownloadDocument;

@Repository
public interface DownloadDocumentRepository extends JpaRepository<DownloadDocument, Long> {
    // Add custom queries if needed
}
