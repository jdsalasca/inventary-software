package com.sena.inventarioback.interfaces;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import com.sena.inventarioback.dto.PhysicalDocumentDTO;
import com.sena.inventarioback.utils.response.DefaultResponse;

public interface IPhysicalDocumentService {
    ResponseEntity<DefaultResponse<PhysicalDocumentDTO>> createPhysicalDocument(PhysicalDocumentDTO physicalDocumentDTO);

    ResponseEntity<DefaultResponse<PhysicalDocumentDTO>> getPhysicalDocumentById(Long id);

    ResponseEntity<DefaultResponse<Page<PhysicalDocumentDTO>>> getAllPhysicalDocuments(Pageable pageable);

    ResponseEntity<DefaultResponse<PhysicalDocumentDTO>> updatePhysicalDocument(Long id, PhysicalDocumentDTO physicalDocumentDTO);

    void deletePhysicalDocument(Long id);

	ResponseEntity<DefaultResponse<PhysicalDocumentDTO>> getAllPhysicalDocumets();
}
