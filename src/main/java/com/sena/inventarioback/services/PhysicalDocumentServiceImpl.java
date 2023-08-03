package com.sena.inventarioback.services;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.sena.inventarioback.dto.BoxDTO;
import com.sena.inventarioback.dto.PhysicalDocumentDTO;
import com.sena.inventarioback.interfaces.IPhysicalDocumentService;
import com.sena.inventarioback.models.PhysicalDocument;
import com.sena.inventarioback.repositories.PhysicalDocumentRepository;
import com.sena.inventarioback.utils.response.DefaultResponse;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PhysicalDocumentServiceImpl implements IPhysicalDocumentService {

    private final PhysicalDocumentRepository physicalDocumentRepository;
    private final ModelMapper modelMapper;

    @Override
    public ResponseEntity<DefaultResponse<PhysicalDocumentDTO>> createPhysicalDocument(PhysicalDocumentDTO physicalDocumentDTO) {
        var physicalDocument = modelMapper.map(physicalDocumentDTO, PhysicalDocument.class);
        physicalDocument.setCreatedAt(LocalDateTime.now());
        physicalDocument.setUpdatedAt(LocalDateTime.now());
        var savedDocument = physicalDocumentRepository.save(physicalDocument);
        return DefaultResponse.onThrow200Response(modelMapper.map(savedDocument, PhysicalDocumentDTO.class));
    }

    @Override
    public ResponseEntity<DefaultResponse<PhysicalDocumentDTO>> getPhysicalDocumentById(Long id) {
        var physicalDocument = physicalDocumentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Physical document not found with id: " + id));
        return DefaultResponse.onThrow200Response(modelMapper.map(physicalDocument, PhysicalDocumentDTO.class));
    }

    @Override
    public ResponseEntity<DefaultResponse<Page<PhysicalDocumentDTO>>> getAllPhysicalDocuments(Pageable pageable) {
        var physicalDocumentPage = physicalDocumentRepository.findAll(pageable);
        Page<PhysicalDocumentDTO> physicalDocumentDTOPage = physicalDocumentPage.map(doc -> modelMapper.map(doc, PhysicalDocumentDTO.class));
        return DefaultResponse.onThrow200Response(physicalDocumentDTOPage);
    }

    @Override
    public ResponseEntity<DefaultResponse<PhysicalDocumentDTO>> updatePhysicalDocument(Long id, PhysicalDocumentDTO physicalDocumentDTO) {
        var existingDocument = physicalDocumentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Physical document not found with id: " + id));

        modelMapper.map(physicalDocumentDTO, existingDocument);
        existingDocument.setId(id);
        existingDocument.setUpdatedAt(LocalDateTime.now());
        var updatedDocument = physicalDocumentRepository.save(existingDocument);
        return DefaultResponse.onThrow200Response(modelMapper.map(updatedDocument, PhysicalDocumentDTO.class));
    }

    @Override
    public void deletePhysicalDocument(Long id) {
        var existingDocument = physicalDocumentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Physical document not found with id: " + id));
        physicalDocumentRepository.delete(existingDocument);
    }

	@Override
	public ResponseEntity<DefaultResponse<PhysicalDocumentDTO>> getAllPhysicalDocumets() {
		
		return DefaultResponse.onHandlerFindJpa(physicalDocumentRepository.findAll().stream()
                .map(physicalDocument->   modelMapper.map(physicalDocument, PhysicalDocumentDTO.class))
                .collect(Collectors.toList())
				);
	}
}