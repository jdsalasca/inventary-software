package com.sena.inventarioback.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sena.inventarioback.models.DocumentType;
import com.sena.inventarioback.models.Gender;

public interface DocumentTypeRepository extends JpaRepository<DocumentType, Integer> {

}
