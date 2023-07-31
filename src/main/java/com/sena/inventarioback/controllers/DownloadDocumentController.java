package com.sena.inventarioback.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sena.inventarioback.dto.DownloadDocumentDTO;
import com.sena.inventarioback.interfaces.IDownloadDocumentService;
import com.sena.inventarioback.utils.response.DefaultResponse;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/download-documents")
@RequiredArgsConstructor
public class DownloadDocumentController {

    private final IDownloadDocumentService downloadDocumentService;

    @PostMapping
    public ResponseEntity<DefaultResponse<DownloadDocumentDTO>> createDownloadDocument(
            @RequestBody DownloadDocumentDTO downloadDocumentDTO) {
        return downloadDocumentService.createDownloadDocument(downloadDocumentDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DefaultResponse<DownloadDocumentDTO>> getDownloadDocumentById(@PathVariable Long id) {
        return downloadDocumentService.getDownloadDocumentById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DefaultResponse<DownloadDocumentDTO>> updateDownloadDocument(@PathVariable Long id,
            @RequestBody DownloadDocumentDTO downloadDocumentDTO) {
        return downloadDocumentService.updateDownloadDocument(id, downloadDocumentDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDownloadDocument(@PathVariable Long id) {
        downloadDocumentService.deleteDownloadDocument(id);
        return ResponseEntity.noContent().build();
    }

    // Add other CRUD endpoints if needed
}
