package com.sena.inventarioback.services;


import java.time.LocalDateTime;

import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.sena.inventarioback.dto.DownloadDocumentDTO;
import com.sena.inventarioback.interfaces.IDownloadDocumentService;
import com.sena.inventarioback.models.DownloadDocument;
import com.sena.inventarioback.repositories.DownloadDocumentRepository;
import com.sena.inventarioback.utils.response.DefaultResponse;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DownloadDocumentServiceImpl implements IDownloadDocumentService {

    private final DownloadDocumentRepository downloadDocumentRepository;
    private final ModelMapper modelMapper;

    @Override
    public ResponseEntity<DefaultResponse<DownloadDocumentDTO>> createDownloadDocument(DownloadDocumentDTO downloadDocumentDTO) {
        var downloadDocument = modelMapper.map(downloadDocumentDTO, DownloadDocument.class);
        downloadDocument.setCreatedAt(LocalDateTime.now());
        downloadDocument.setUpdatedAt(LocalDateTime.now());
        var savedDocument = downloadDocumentRepository.save(downloadDocument);
        return DefaultResponse.onThrow200Response(modelMapper.map(savedDocument, DownloadDocumentDTO.class));
    }

    @Override
    public ResponseEntity<DefaultResponse<DownloadDocumentDTO>> getDownloadDocumentById(Long id) {
        var downloadDocument = downloadDocumentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Download document not found with id: " + id));
        return DefaultResponse.onThrow200Response(modelMapper.map(downloadDocument, DownloadDocumentDTO.class));
    }

    @Override
    public ResponseEntity<DefaultResponse<DownloadDocumentDTO>> updateDownloadDocument(Long id, DownloadDocumentDTO downloadDocumentDTO) {
        var existingDocument = downloadDocumentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Download document not found with id: " + id));

        modelMapper.map(downloadDocumentDTO, existingDocument);
        existingDocument.setUpdatedAt(LocalDateTime.now());
        var updatedDocument = downloadDocumentRepository.save(existingDocument);
        return DefaultResponse.onThrow200Response(modelMapper.map(updatedDocument, DownloadDocumentDTO.class));
    }

    @Override
    public void deleteDownloadDocument(Long id) {
        var existingDocument = downloadDocumentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Download document not found with id: " + id));
        downloadDocumentRepository.delete(existingDocument);
    }

    // Implement other CRUD operations if needed
}