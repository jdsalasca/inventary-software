package com.sena.inventarioback.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoadDocumentDTO {
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDate startDate;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDate dueDate;

    @NotNull(message = "physicalDocumentId is required")
    private Long physicalDocumentId;

    @NotNull(message = "idUser is required")
    private Long idUser;

    @NotNull(message = "idStatus is required")
    private Long idStatus;
}
