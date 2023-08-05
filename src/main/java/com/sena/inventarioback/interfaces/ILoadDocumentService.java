package com.sena.inventarioback.interfaces;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import com.sena.inventarioback.dto.LoadDocumentDTO;
import com.sena.inventarioback.utils.response.DefaultResponse;
public interface ILoadDocumentService {
    ResponseEntity<DefaultResponse<LoadDocumentDTO>> createLoadDocument(LoadDocumentDTO loadDocumentDTO);

    ResponseEntity<DefaultResponse<LoadDocumentDTO>> getLoadDocumentById(Long id);

    ResponseEntity<DefaultResponse<LoadDocumentDTO>> getAllLoadDocuments(Pageable pageable);

    ResponseEntity<DefaultResponse<LoadDocumentDTO>> updateLoadDocument(Long id, LoadDocumentDTO loadDocumentDTO);

    void deleteLoadDocument(Long id);

	ResponseEntity<DefaultResponse<LoadDocumentDTO>> geAllLoadDocumentByIdUserAndStatusId(Long idUser, Long idStatus);

	ResponseEntity<DefaultResponse<LoadDocumentDTO>> getAllLoadDocumentsByStatusId(Long idStatus);
}
