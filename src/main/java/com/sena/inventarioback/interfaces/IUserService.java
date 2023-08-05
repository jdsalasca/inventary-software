package com.sena.inventarioback.interfaces;

import java.util.List;

import javax.security.auth.login.AccountNotFoundException;

import org.springframework.http.ResponseEntity;

import com.sena.inventarioback.dto.GeneralResourcesDTO;
import com.sena.inventarioback.dto.UserAccessDto;
import com.sena.inventarioback.dto.UserDTO;
import com.sena.inventarioback.models.User;
import com.sena.inventarioback.repositories.UserRepository;
import com.sena.inventarioback.utils.crud.ICrudInterface;
import com.sena.inventarioback.utils.response.DefaultResponse;

import reactor.core.publisher.Flux;

public interface IUserService extends ICrudInterface<User, UserDTO,  Integer, UserRepository> {

	List<User> findByDocumentTypeId(Integer documentTypeId);

	ResponseEntity<DefaultResponse<User>> test();

	ResponseEntity<DefaultResponse<UserAccessDto>> login(String userName, String password) throws AccountNotFoundException;

	List<String> onConcurrentEndPoint();

	Flux<String> onConcurrentEndPoint2();

	ResponseEntity<DefaultResponse<UserDTO>> deleteById(Long id);

	ResponseEntity<DefaultResponse<GeneralResourcesDTO>> findAllGeneralResources();


}
