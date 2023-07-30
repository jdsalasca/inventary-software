package com.sena.inventarioback.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sena.inventarioback.models.User;

public interface UserRepository extends JpaRepository<User, Integer> {

	Optional<User> findByUserName(String userName);

	List<User> findAllByDocumentType(Integer documentTypeId);

}
