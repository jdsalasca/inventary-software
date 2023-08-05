package com.sena.inventarioback.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sena.inventarioback.models.SpecialRequirement;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class SpecialRequirementDTO {
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	private Long id;
	  @NotBlank(message = "description is required")
    private String description;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDateTime createdAt;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDateTime updatedAt;
    @NotNull(message = "userId is required")
    private Long userId;
    
    public SpecialRequirementDTO(SpecialRequirement requirement) {
    	this.id = requirement.getId();
    	this.description = requirement.getDescription();
    	this.createdAt = requirement.getCreatedAt();
    	this.updatedAt = requirement.getUpdatedAt();
    	this.userId = requirement.getUserId();
    }
}