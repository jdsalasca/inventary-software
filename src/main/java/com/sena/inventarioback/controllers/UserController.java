package com.sena.inventarioback.controllers;

import java.util.List;

import javax.security.auth.login.AccountNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sena.inventarioback.dto.PersonDTO;
import com.sena.inventarioback.interfaces.IPersonService;
import com.sena.inventarioback.models.Person;
import com.sena.inventarioback.utils.response.DefaultResponse;
import com.sena.inventarioback.utils.response.ObjectResponse;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

//API-RestFul
@RestController
@Slf4j
@RequestMapping("/people")
@AllArgsConstructor
public class UserController {

	private final IPersonService iPersonService;

	@GetMapping("")
	public ResponseEntity<DefaultResponse<Person>> findAllPagination(
			@RequestParam(name = "page", defaultValue = "0") Integer page,
			@RequestParam(name = "size", defaultValue = "500") Integer size,
			@RequestParam(name = "orderBy", defaultValue = "id") String orderBy) {
		return iPersonService.findAllPaginationSizePageOrderBy(size, page, orderBy);
	}

	@GetMapping("general")
	public ResponseEntity<DefaultResponse<Person>> findAll() {
		return iPersonService.findAll();
	}

	@GetMapping("general2")
	public ResponseEntity<com.jdsalasca.defaultresponse.DefaultResponse<Person>> findAll2() {
		return iPersonService.test();
	}

	// EndPoint
	@GetMapping("{id}")
	public ResponseEntity<DefaultResponse<Person>> findById(@PathVariable Integer id) {
		return iPersonService.findById(id);
	}

	// EndPoint
	@GetMapping("/document-type/{documentTypeId}")
	public List<Person> findByDocumentTypeId(@PathVariable Integer documentTypeId) {
		return iPersonService.findByDocumentTypeId(documentTypeId);
	}

	@PostMapping("")
	public ResponseEntity<DefaultResponse<Person>> createPerson(@Validated @RequestBody PersonDTO personDTO,
			BindingResult bindingResult) {
		log.info("user which is performing the operation {}", "not implement yet");
		return iPersonService.save(personDTO, bindingResult, Person.class);
	}

	@PutMapping("/{id}")
	public ResponseEntity<DefaultResponse<Person>> updatePerson(@PathVariable Integer id,
			@Validated @RequestBody PersonDTO personDTO, BindingResult bindingResult) {
		log.info("user which is performing the operation {}", "not implement yet");
		return iPersonService.update(id, personDTO, bindingResult, Person.class);
	}

	// EndPoint
	@GetMapping("/login/username/{username}/password/{password}")
	public Boolean findByDocumentTypeId(@PathVariable String username, @PathVariable String password)
			throws AccountNotFoundException {
		return iPersonService.login(username, password);
	}
	
	
	

	// EndPoint
	@GetMapping("integration")
	public List<String> testWebClient() {
		log.info("starting transaction");
		return iPersonService.onConcurrentEndPoint();
	}
	

	// EndPoint
	@GetMapping("integration2")
	public Flux<String> testWebClient2() {
		log.info("starting transaction");
		return iPersonService.onConcurrentEndPoint2();
	}

}
