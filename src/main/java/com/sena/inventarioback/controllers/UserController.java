package com.sena.inventarioback.controllers;

import java.util.List;

import javax.security.auth.login.AccountNotFoundException;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sena.inventarioback.dto.UserDTO;
import com.sena.inventarioback.interfaces.IUserService;
import com.sena.inventarioback.models.User;
import com.sena.inventarioback.utils.response.DefaultResponse;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

//API-RestFul
@RestController
@Slf4j
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {

	private final IUserService iUserService;

	@GetMapping("")
	public ResponseEntity<DefaultResponse<User>> findAllPagination(
			@RequestParam(name = "page", defaultValue = "0") Integer page,
			@RequestParam(name = "size", defaultValue = "500") Integer size,
			@RequestParam(name = "orderBy", defaultValue = "id") String orderBy) {
		return iUserService.findAllPaginationSizePageOrderBy(size, page, orderBy);
	}

	@GetMapping("general")
	public ResponseEntity<DefaultResponse<User>> findAll() {
		return iUserService.findAll();
	}

	@GetMapping("general2")
	public ResponseEntity<DefaultResponse<User>> findAll2() {
		return  iUserService.test();
	}

	// EndPoint
	@GetMapping("{id}")
	public ResponseEntity<DefaultResponse<User>> findById(@PathVariable Integer id) {
		return iUserService.findById(id);
	}

	// EndPoint
	@GetMapping("/document-type/{documentTypeId}")
	public List<User> findByDocumentTypeId(@PathVariable Integer documentTypeId) {
		return iUserService.findByDocumentTypeId(documentTypeId);
	}
	@PatchMapping
	public List<User> pathEntity(@PathVariable Integer documentTypeId) {
		return iUserService.findByDocumentTypeId(documentTypeId);
	}

	@PostMapping("")
	public ResponseEntity<DefaultResponse<User>> createPerson(@Validated @RequestBody UserDTO personDTO,
			BindingResult bindingResult) {
		log.info("user which is performing the operation {}", "not implement yet");
		return iUserService.save(personDTO, bindingResult, User.class);
	}

	@PutMapping("/{id}")
	public ResponseEntity<DefaultResponse<User>> updatePerson(@PathVariable Integer id,
			@Validated @RequestBody UserDTO personDTO, BindingResult bindingResult) {
		return iUserService.update(id, personDTO, bindingResult, User.class);
	}

	// EndPoint
	@GetMapping("/login/username/{username}/password/{password}")
	public Boolean findByDocumentTypeId(@PathVariable String username, @PathVariable String password)
			throws AccountNotFoundException {
		return iUserService.login(username, password);
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<DefaultResponse<UserDTO>> deleteUserById(@PathVariable Long id) {
		
		return  iUserService.deleteById(id);
	}




	// EndPoint
	@GetMapping("integration")
	public List<String> testWebClient() {
		log.info("starting transaction");
		return iUserService.onConcurrentEndPoint();
	}


	// EndPoint
	@GetMapping("integration2")
	public Flux<String> testWebClient2() {
		log.info("starting transaction");
		return iUserService.onConcurrentEndPoint2();
	}

}
