package com.sena.inventarioback.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sena.inventarioback.models.Box;

@Repository
public interface BoxRepository extends JpaRepository<Box, Long> {
}
