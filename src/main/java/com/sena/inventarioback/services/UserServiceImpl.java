package com.sena.inventarioback.services;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import javax.security.auth.login.AccountNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.sena.inventarioback.dto.UserAccessDto;
import com.sena.inventarioback.dto.UserDTO;
import com.sena.inventarioback.integrations.IPeopleIntegration;
import com.sena.inventarioback.interfaces.IUserService;
import com.sena.inventarioback.models.User;
import com.sena.inventarioback.repositories.UserRepository;
import com.sena.inventarioback.utils.crud.CrudServiceImpl;
import com.sena.inventarioback.utils.response.DefaultResponse;

import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

@Service
@Component
@Slf4j
public class UserServiceImpl extends CrudServiceImpl<User, UserDTO, Integer, UserRepository>
		implements IUserService {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private IPeopleIntegration iPeopleIntegration;

	@Override
	public ResponseEntity<DefaultResponse<User>> save(UserDTO dto, BindingResult bindingResult,
			Class<User> entityClass) {
		dto.setCreatedAt(LocalDateTime.now());
		dto.setUpdatedAt(LocalDateTime.now());
		dto.setPassword(BCrypt.hashpw(dto.getPassword(), BCrypt.gensalt()));
		return super.save(dto, bindingResult, entityClass);
	}

	@Override
	public ResponseEntity<DefaultResponse<User>> update(Integer id, UserDTO dto, BindingResult bindigResult,
			Class<User> entityClass) {
		dto.setPassword(BCrypt.hashpw(dto.getPassword(), BCrypt.gensalt()));
		dto.setUpdatedAt(LocalDateTime.now());
		
		dto.setPassword(BCrypt.hashpw(dto.getPassword(), BCrypt.gensalt()));
		return super.update(id, dto, bindigResult, entityClass);
	}

	@Override
	public ResponseEntity<DefaultResponse<UserAccessDto>> login(String userName, String password) throws AccountNotFoundException {
	    User user =   userRepository.findByUserName(userName).orElseThrow(() -> new AccountNotFoundException("Usuario no encontrado")) ;

	    var storedPassword = user.getPassword();
	    boolean isValid;
	    
	    if (isBCryptEncoded(storedPassword)) {
	    	isValid = BCrypt.checkpw(password, storedPassword);
	    } else {
	        // Assuming storedPassword is a raw (plaintext) string
	    	isValid = storedPassword.equals(password);
	    }
	    if(isValid) {
	    	return DefaultResponse.onThrow200Response(new UserAccessDto(user, isValid));
	    }else {
	    	return DefaultResponse.onThrow400ResponseTypeInfo("Credenciales invalidas");
	    }
	}
	// Helper method to check if the password is BCrypt encoded
	private boolean isBCryptEncoded(String password) {
	    return password.startsWith("$2a$");
	}

	@Override
	public List<User> findByDocumentTypeId(Integer documentTypeId) {
		return userRepository.findAllByDocumentType(documentTypeId);
	}

	@Override
	@Cacheable("allpeople")
	public ResponseEntity<DefaultResponse<User>> findAll() {
		log.info("consumig real service");
		//return super.findAll();
		return super.findAll();
	}

	@Override
	public ResponseEntity<DefaultResponse<User>> findById(Integer id) {
		var person = userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado en el sistema"));

		return DefaultResponse.onThrow200Response(Collections.singletonList(person));
	}

	@Override
	public List<String> onConcurrentEndPoint() {
		return Flux.range(0, 10).flatMap(i -> iPeopleIntegration.onGetUserById(78).map(Object::toString))
				.collect(Collectors.toList()).block();
	}

	@Override
	public Flux<String> onConcurrentEndPoint2() {
		return Flux.range(0, 10).flatMap(i -> iPeopleIntegration.onGetUserById(78).map(Object::toString)).collectList()
				.flatMapMany(Flux::fromIterable);
	}

	@Override
	public ResponseEntity<DefaultResponse<User>> test() {
		// TODO Auto-generated method stub
		return DefaultResponse.onThrow200Response(Collections.emptyList());
	}

	@Override
	public ResponseEntity<DefaultResponse<UserDTO>> deleteById(Long id) {
		userRepository.deleteById(Integer.valueOf(id.toString()));
		return DefaultResponse.onThrow200Response(Collections.emptyList(), "Usuario con id: "+ id+ " Eliminado exitosamente");
	}

}
