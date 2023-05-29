package com.sena.inventarioback.services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import javax.security.auth.login.AccountNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.sena.inventarioback.dto.PersonDTO;
import com.sena.inventarioback.integrations.IPeopleIntegration;
import com.sena.inventarioback.interfaces.IPersonService;
import com.sena.inventarioback.models.Person;
import com.sena.inventarioback.repositories.PersonRepository;
import com.sena.inventarioback.utils.crud.CrudServiceImpl;
import com.sena.inventarioback.utils.response.DefaultResponse;
import com.sena.inventarioback.utils.response.DefaultResponse.MESSAGETYPES;
import com.sena.inventarioback.utils.response.ObjectResponse;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Component
@Slf4j
public class PersonServiceImpl extends CrudServiceImpl<Person, PersonDTO, Integer, PersonRepository>
		implements IPersonService {
	@Autowired
	private PersonRepository personRepository;
	@Autowired
	private IPeopleIntegration iPeopleIntegration;

	@Override
	public ResponseEntity<DefaultResponse<Person>> save(PersonDTO dto, BindingResult bindingResult,
			Class<Person> entityClass) {
		dto.setCreatedAt(LocalDateTime.now());
		dto.setUpdatedAt(LocalDateTime.now());
		dto.setPassword(BCrypt.hashpw(dto.getPassword(), BCrypt.gensalt()));
		return super.save(dto, bindingResult, entityClass);
	}

	@Override
	public ResponseEntity<DefaultResponse<Person>> update(Integer id, PersonDTO dto, BindingResult bindigResult,
			Class<Person> entityClass) {
		dto.setPassword(BCrypt.hashpw(dto.getPassword(), BCrypt.gensalt()));
		dto.setUpdatedAt(LocalDateTime.now());
		dto.setPassword(BCrypt.hashpw(dto.getPassword(), BCrypt.gensalt()));
		return super.update(id, dto, bindigResult, entityClass);
	}

	@Override
	public Boolean login(String userName, String password) throws AccountNotFoundException {
		Optional<Person> person = personRepository.findByUserName(userName);
		if (person.isEmpty()) {
			throw new AccountNotFoundException("User Not found");
		}
		return BCrypt.checkpw(password, person.get().getPassword());
	}

	@Override
	public List<Person> findByDocumentTypeId(Integer documentTypeId) {
		return personRepository.findAllByDocumentType(documentTypeId);
	}

	@Override
	@Cacheable("allpeople")
	public ResponseEntity<DefaultResponse<Person>> findAll() {
		log.info("consumig real service");
		//return super.findAll();
		return super.findAll();
	}

	@Override
	public ResponseEntity<DefaultResponse<Person>> findById(Integer id) {
		Person person = personRepository.findById(id).orElse(new Person());
		log.warn("Siu");
		Predicate<Person> isOld = personL -> personL.getDocumentNumber().equals(3l);
		if (isOld.test(person)) {
			return DefaultResponse.onThrow200Response(Collections.singletonList(person));
		}
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
	public ResponseEntity<com.jdsalasca.defaultresponse.DefaultResponse<Person>> test() {
		// TODO Auto-generated method stub
		return com.jdsalasca.defaultresponse.DefaultResponse.onThrow200Response(Collections.emptyList());
	}

}
