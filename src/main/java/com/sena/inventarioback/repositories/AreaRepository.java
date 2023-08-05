package com.sena.inventarioback.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sena.inventarioback.models.Area;
import com.sena.inventarioback.models.Gender;
import com.sena.inventarioback.models.Status;

public interface AreaRepository extends JpaRepository<Area, Integer> {

}
