package com.sena.inventarioback.models;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
@Entity
@Data
@Table(name = "digital_document")
@AllArgsConstructor
@NoArgsConstructor
public class DigitalDocument {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Description is required")
    private String descrip;

    @Column(nullable = false)
    private String file;

    @Column(nullable = false, updatable = false, name = "createdAt")
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(nullable = false, name = "updatedAt")
    @UpdateTimestamp
    private LocalDateTime updatedAt;

}