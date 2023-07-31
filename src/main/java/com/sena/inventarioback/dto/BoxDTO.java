package com.sena.inventarioback.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class BoxDTO {

    private Long id;

    @NotBlank(message = "Description is required")
    private String descrip;

    @NotBlank(message = "Reference is required")
    private String ref;
}
