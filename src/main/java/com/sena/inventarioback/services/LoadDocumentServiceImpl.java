package com.sena.inventarioback.services;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.sena.inventarioback.dto.LoadDocumentDTO;
import com.sena.inventarioback.dto.PhysicalDocumentDTO;
import com.sena.inventarioback.interfaces.ILoadDocumentService;
import com.sena.inventarioback.models.LoadDocument;
import com.sena.inventarioback.repositories.LoadDocumentRepository;
import com.sena.inventarioback.utils.response.DefaultResponse;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LoadDocumentServiceImpl implements ILoadDocumentService {

	static final int DAYS_FOR_LOAD = 15;
	private final LoadDocumentRepository loadDocumentRepository;
	private final ModelMapper modelMapper;

	@Override
	public ResponseEntity<DefaultResponse<LoadDocumentDTO>> createLoadDocument(LoadDocumentDTO loadDocumentDTO) {
		var loadDocument = modelMapper.map(loadDocumentDTO, LoadDocument.class);
		loadDocument.setCreatedAt(LocalDateTime.now());
		loadDocument.setUpdatedAt(LocalDateTime.now());
		loadDocument.setStartDate(LocalDate.now());
		loadDocument.setDueDate(LocalDate.now().plusDays(DAYS_FOR_LOAD));
		var savedDocument = loadDocumentRepository.save(loadDocument);
		return DefaultResponse.onThrow200Response(modelMapper.map(savedDocument, LoadDocumentDTO.class));
	}

	@Override
	public ResponseEntity<DefaultResponse<LoadDocumentDTO>> getLoadDocumentById(Long id) {
		var loadDocument = loadDocumentRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Load document not found with id: " + id));
		return DefaultResponse.onThrow200Response(modelMapper.map(loadDocument, LoadDocumentDTO.class));
	}

	@Override
	public ResponseEntity<DefaultResponse<Page<LoadDocumentDTO>>> getAllLoadDocuments(Pageable pageable) {
		var loadDocumentPage = loadDocumentRepository.findAll(pageable);
		Page<LoadDocumentDTO> loadDocumentDTOPage = loadDocumentPage
				.map(doc -> modelMapper.map(doc, LoadDocumentDTO.class));
		return DefaultResponse.onThrow200Response(loadDocumentDTOPage);
	}

	@Override
	public ResponseEntity<DefaultResponse<LoadDocumentDTO>> updateLoadDocument(Long id,
			LoadDocumentDTO loadDocumentDTO) {
		LoadDocument existingDocument = loadDocumentRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Load document not found with id: " + id));

		modelMapper.map(loadDocumentDTO, existingDocument);
		existingDocument.setUpdatedAt(LocalDateTime.now());
		existingDocument.setId(id);
		var updatedDocument = loadDocumentRepository.save(existingDocument);
		return DefaultResponse.onThrow200Response(modelMapper.map(updatedDocument, LoadDocumentDTO.class));
	}

	@Override
	public void deleteLoadDocument(Long id) {
		var existingDocument = loadDocumentRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Load document not found with id: " + id));
		loadDocumentRepository.delete(existingDocument);
	}

	@Override
	public ResponseEntity<DefaultResponse<LoadDocumentDTO>> geAllLoadDocumentByIdUserAndStatusId(Long idUser,
			Long idStatus) {

		return DefaultResponse.onHandlerFindJpa(loadDocumentRepository.findAllByIdUserAndIdStatus(idUser, idStatus)
				.stream().map(loadDocument -> modelMapper.map(loadDocument, LoadDocumentDTO.class))
				.collect(Collectors.toList())

		);
	}

	@Override
	public ResponseEntity<DefaultResponse<LoadDocumentDTO>> getAllLoadDocumentsByStatusId(Long idStatus) {
		return DefaultResponse.onHandlerFindJpa(loadDocumentRepository.findAllByIdStatus(idStatus)
				.stream().map(loadDocument -> modelMapper.map(loadDocument, LoadDocumentDTO.class))
				.collect(Collectors.toList())

		);
	}

}