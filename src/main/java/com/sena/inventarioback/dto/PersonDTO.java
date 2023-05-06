package com.sena.inventarioback.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.validation.constraints.*;
import lombok.*;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonDTO {
    @NotBlank(message = "First name cannot be blank")
    private String firstName;
    @NotBlank(message = "Last name cannot be blank")
    private String lastName;
    @NotBlank(message = "Email cannot be blank")
    @Email(message = "Invalid email format")
    private String email;
    @NotBlank(message = "cellphone cannot be blank")
    private String cellphone;
    @NotNull(message = "dateOfBirthh cannot be null")
    @Past(message = "dateOfBirth must be in the past")
    private LocalDate dateOfBirth;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt = LocalDateTime.now();
    @NotNull(message = "gender cannot be null")
    private Integer gender;
    @NotNull(message = "documentType cannot be null")
    private Integer documentType;
    @NotNull(message = "Status cannot be null")
    private Integer status;
    @NotBlank(message = "documentNumber cannot be blank")
    private String documentNumber;
    @NotBlank(message = "password cannot be blank")
    private String password;
    @NotBlank(message = "userName cannot be blank")
    private String userName;
}
