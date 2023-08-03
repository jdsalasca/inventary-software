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

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/digital-documents")
@RequiredArgsConstructor
@Tag(name = "Digital Documents", description = "Endpoints related to managing digital documents> Los documentos digitales son aquellos que no tienen una represetancion fisica, porque estan almacenados en la nube")

public class DigitalDocumentController {

	private final IDigitalDocumentService digitalDocumentService;

	@PostMapping("")
	@Operation(summary = "Metodo para crear un documento digital", description = "", tags = "create")
	@ApiResponse(responseCode = "200", description = "Successful response with the created digital document data", content = @Content(mediaType = "application/json"))
	@ApiResponse(responseCode = "400", description = "Bad Request. Indicates that the provided data is invalid or missing", content = @Content(mediaType = "application/json", schema = @Schema(implementation = DefaultResponse.class)))
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
