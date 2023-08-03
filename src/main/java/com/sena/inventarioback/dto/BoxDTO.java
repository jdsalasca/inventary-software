package com.sena.inventarioback.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class BoxDTO {
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    @NotBlank(message = "Description is required")
    private String descrip;

    @NotBlank(message = "Reference is required")
    private String ref;
}
