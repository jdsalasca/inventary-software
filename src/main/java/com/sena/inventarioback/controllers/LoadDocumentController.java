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

import com.sena.inventarioback.dto.LoadDocumentDTO;
import com.sena.inventarioback.interfaces.ILoadDocumentService;
import com.sena.inventarioback.utils.response.DefaultResponse;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/load-documents")
@RequiredArgsConstructor
public class LoadDocumentController {

    private final ILoadDocumentService loadDocumentService;

    @PostMapping
    public ResponseEntity<DefaultResponse<LoadDocumentDTO>> createLoadDocument(
            @Valid @RequestBody LoadDocumentDTO loadDocumentDTO) {

        return loadDocumentService.createLoadDocument(loadDocumentDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DefaultResponse<LoadDocumentDTO>> getLoadDocumentById(@PathVariable Long id) {

        return loadDocumentService.getLoadDocumentById(id);
    }
    @GetMapping("/idUser/{idUser}/idStatus/{idStatus}")
    public ResponseEntity<DefaultResponse<LoadDocumentDTO>> geAllLoadDocumentByIdUserAndStatusId(@PathVariable Long idUser, @PathVariable Long idStatus) {

        return loadDocumentService.geAllLoadDocumentByIdUserAndStatusId(idUser, idStatus);
    }

    @GetMapping
    public ResponseEntity<DefaultResponse<Page<LoadDocumentDTO>>> getAllLoadDocuments(Pageable pageable) {
        return loadDocumentService.getAllLoadDocuments(pageable);
    }
    @GetMapping("/idStatus/{idStatus}")
    public ResponseEntity<DefaultResponse<LoadDocumentDTO>> getAllLoadDocumentsByStatusId( @PathVariable Long idStatus) {
        return loadDocumentService.getAllLoadDocumentsByStatusId(idStatus);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DefaultResponse<LoadDocumentDTO>> updateLoadDocument(@PathVariable Long id,
            @Valid @RequestBody LoadDocumentDTO loadDocumentDTO) {
        return loadDocumentService.updateLoadDocument(id, loadDocumentDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLoadDocument(@PathVariable Long id) {
        loadDocumentService.deleteLoadDocument(id);
        return ResponseEntity.noContent().build();
    }
}
