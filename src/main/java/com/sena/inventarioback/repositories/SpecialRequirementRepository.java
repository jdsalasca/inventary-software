package com.sena.inventarioback.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sena.inventarioback.models.SpecialRequirement;

@Repository
public interface SpecialRequirementRepository extends JpaRepository<SpecialRequirement, Long> {
}
