package com.sena.inventarioback.controllers;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sena.inventarioback.dto.PhysicalDocumentDTO;
import com.sena.inventarioback.interfaces.IPhysicalDocumentService;
import com.sena.inventarioback.utils.response.DefaultResponse;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/physical-documents")
@RequiredArgsConstructor
public class PhysicalDocumentController {

    private final IPhysicalDocumentService physicalDocumentService;

    @PostMapping
    public ResponseEntity<DefaultResponse<PhysicalDocumentDTO>> createPhysicalDocument(
            @Valid @RequestBody PhysicalDocumentDTO physicalDocumentDTO) {

        return physicalDocumentService.createPhysicalDocument(physicalDocumentDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DefaultResponse<PhysicalDocumentDTO>> getPhysicalDocumentById(@PathVariable Long id) {

        return physicalDocumentService.getPhysicalDocumentById(id);
    }
    @GetMapping("")
    public ResponseEntity<DefaultResponse<PhysicalDocumentDTO>> getAllPhysicalDocumets() {

        return physicalDocumentService.getAllPhysicalDocumets();
    }


    @GetMapping("/pageable")
    public ResponseEntity<DefaultResponse<PhysicalDocumentDTO>> getAllPhysicalDocuments(Pageable pageable) {
        return physicalDocumentService.getAllPhysicalDocuments(pageable);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DefaultResponse<PhysicalDocumentDTO>> updatePhysicalDocument(@PathVariable Long id,
            @Valid @RequestBody PhysicalDocumentDTO physicalDocumentDTO) {
        return physicalDocumentService.updatePhysicalDocument(id, physicalDocumentDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePhysicalDocument(@PathVariable Long id) {
        physicalDocumentService.deletePhysicalDocument(id);
        return ResponseEntity.noContent().build();
    }
}
