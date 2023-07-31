package com.sena.inventarioback.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DownloadDocumentDTO {
    private Long id;
	@NotNull(message = "digitalDocumentId is required")
    private Long digitalDocumentId;
	@NotNull(message = "idUser is required")
    private Long idUser;
}