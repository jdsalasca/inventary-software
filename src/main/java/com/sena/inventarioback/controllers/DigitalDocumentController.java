package com.sena.inventarioback.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sena.inventarioback.dto.DigitalDocumentDTO;
import com.sena.inventarioback.interfaces.IDigitalDocumentService;
import com.sena.inventarioback.utils.response.DefaultResponse;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/digital-documents")
@RequiredArgsConstructor
public class DigitalDocumentController {

	private final IDigitalDocumentService digitalDocumentService;

	@PostMapping
	public ResponseEntity<DefaultResponse<DigitalDocumentDTO>> createDigitalDocument(
			@Valid @RequestBody DigitalDocumentDTO digitalDocumentDTO) {

		return digitalDocumentService.createDigitalDocument(digitalDocumentDTO);
	}

	@GetMapping("/{id}")
	public ResponseEntity<DefaultResponse<DigitalDocumentDTO>> getDigitalDocumentById(@PathVariable Long id) {

		return digitalDocumentService.getDigitalDocumentById(id);
	}
	@GetMapping("")
	public ResponseEntity<DefaultResponse<DigitalDocumentDTO>> getAllDigitalDocuments() {

		return digitalDocumentService.getAllDigitalDocuments();
	}


	@PutMapping("/{id}")
	public ResponseEntity<DefaultResponse<DigitalDocumentDTO>> updateDigitalDocument(@PathVariable Long id,
			@Valid @RequestBody DigitalDocumentDTO digitalDocumentDTO) {
		return digitalDocumentService.updateDigitalDocument(id, digitalDocumentDTO);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteDigitalDocument(@PathVariable Long id) {
		digitalDocumentService.deleteDigitalDocument(id);
		return ResponseEntity.noContent().build();
	}

}
