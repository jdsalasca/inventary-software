package com.sena.inventarioback.models;

import jakarta.persistence.*;

import lombok.Data;

@Entity
@Data
@Table(name = "area")
public class Area {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;


}