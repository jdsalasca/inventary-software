package com.sena.inventarioback.interfaces;
import org.springframework.http.ResponseEntity;

import com.sena.inventarioback.dto.DownloadDocumentDTO;
import com.sena.inventarioback.utils.response.DefaultResponse;

public interface IDownloadDocumentService {
    ResponseEntity<DefaultResponse<DownloadDocumentDTO>> createDownloadDocument(DownloadDocumentDTO downloadDocumentDTO);
    ResponseEntity<DefaultResponse<DownloadDocumentDTO>> getDownloadDocumentById(Long id);
    ResponseEntity<DefaultResponse<DownloadDocumentDTO>> updateDownloadDocument(Long id, DownloadDocumentDTO downloadDocumentDTO);
    void deleteDownloadDocument(Long id);
    // Add other CRUD operations if needed
}
