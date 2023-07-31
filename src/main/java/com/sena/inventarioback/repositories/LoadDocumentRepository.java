package com.sena.inventarioback.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sena.inventarioback.models.LoadDocument;

@Repository
public interface LoadDocumentRepository extends JpaRepository<LoadDocument, Long> {

	List<LoadDocument> findAllByIdUserAndIdStatus(Long idUser, Long idStatus);
    // Add custom queries if needed

	List<LoadDocument> findAllByIdStatus(Long idStatus);
}
