package com.sena.inventarioback.services;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.sena.inventarioback.dto.DigitalDocumentDTO;
import com.sena.inventarioback.dto.LoadDocumentDTO;
import com.sena.inventarioback.interfaces.IDigitalDocumentService;
import com.sena.inventarioback.models.DigitalDocument;
import com.sena.inventarioback.repositories.DigitalDocumentRepository;
import com.sena.inventarioback.utils.response.DefaultResponse;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DigitalDocumentServiceImpl implements IDigitalDocumentService {

    private final DigitalDocumentRepository digitalDocumentRepository;
    private final ModelMapper modelMapper;


    @Override
    public ResponseEntity<DefaultResponse<DigitalDocumentDTO>> createDigitalDocument(DigitalDocumentDTO digitalDocumentDTO) {
        var digitalDocument = modelMapper.map(digitalDocumentDTO, DigitalDocument.class);
        digitalDocument.setCreatedAt(LocalDateTime.now());
        digitalDocument.setUpdatedAt(LocalDateTime.now());
        var savedDocument = digitalDocumentRepository.save(digitalDocument);
        return DefaultResponse.onThrow200Response(modelMapper.map(savedDocument, DigitalDocumentDTO.class));
    }

    @Override
    public ResponseEntity<DefaultResponse<DigitalDocumentDTO>> getDigitalDocumentById(Long id) {
        var digitalDocument = digitalDocumentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Digital document not found with id: " + id));
        return DefaultResponse.onThrow200Response(modelMapper.map(digitalDocument, DigitalDocumentDTO.class));
    }

    @Override
    public ResponseEntity<DefaultResponse<DigitalDocumentDTO>> updateDigitalDocument(Long id, DigitalDocumentDTO digitalDocumentDTO) {
        var existingDocument = digitalDocumentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Digital document not found with id: " + id));

        modelMapper.map(digitalDocumentDTO, existingDocument);
        existingDocument.setUpdatedAt(LocalDateTime.now());
        var updatedDocument = digitalDocumentRepository.save(existingDocument);
        return DefaultResponse.onThrow200Response(modelMapper.map(updatedDocument, DigitalDocumentDTO.class));
    }

    @Override
    public void deleteDigitalDocument(Long id) {
        var existingDocument = digitalDocumentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Digital document not found with id: " + id));
        digitalDocumentRepository.delete(existingDocument);
    }

	@Override
	public ResponseEntity<DefaultResponse<DigitalDocumentDTO>> getAllDigitalDocuments() {
		return DefaultResponse.onHandlerFindJpa(digitalDocumentRepository.findAll()
				.stream().map(digitalDocument -> modelMapper.map(digitalDocument, DigitalDocumentDTO.class))
				.collect(Collectors.toList())

		);
	}

}