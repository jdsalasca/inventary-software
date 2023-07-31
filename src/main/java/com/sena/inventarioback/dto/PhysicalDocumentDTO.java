package com.sena.inventarioback.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PhysicalDocumentDTO {

    private Long id;

    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Description is required")
    private String descrip;

    @NotNull(message = "idBox is required")
    private Long idBox;

    @NotNull(message = "idUser is required")
    private Long idUser;

    @NotNull(message = "idStatus is required")
    private Long idStatus;
}
