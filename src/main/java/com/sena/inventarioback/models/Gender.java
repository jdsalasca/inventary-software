package com.sena.inventarioback.models;
import lombok.Data;

import jakarta.persistence.*;

@Entity
@Data
@Table(name = "gender")
public class Gender {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", unique = true, nullable = false, length = 20)
    private String name;
}
