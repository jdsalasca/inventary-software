package com.sena.inventarioback.interfaces;

import org.springframework.http.ResponseEntity;

import com.sena.inventarioback.dto.DigitalDocumentDTO;
import com.sena.inventarioback.utils.response.DefaultResponse;

public interface IDigitalDocumentService {
	ResponseEntity<DefaultResponse<DigitalDocumentDTO>>createDigitalDocument(DigitalDocumentDTO digitalDocumentDTO);
	ResponseEntity<DefaultResponse<DigitalDocumentDTO>> getDigitalDocumentById(Long id);
	ResponseEntity<DefaultResponse<DigitalDocumentDTO>> updateDigitalDocument(Long id, DigitalDocumentDTO digitalDocumentDTO);
    void deleteDigitalDocument(Long id);
	ResponseEntity<DefaultResponse<DigitalDocumentDTO>> getAllDigitalDocuments();
}
