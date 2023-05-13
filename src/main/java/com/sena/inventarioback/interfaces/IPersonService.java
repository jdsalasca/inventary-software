package com.sena.inventarioback.interfaces;

import java.util.List;

import javax.security.auth.login.AccountNotFoundException;

import org.springframework.http.ResponseEntity;

import com.sena.inventarioback.dto.PersonDTO;
import com.sena.inventarioback.models.Person;
import com.sena.inventarioback.repositories.PersonRepository;
import com.sena.inventarioback.utils.crud.ICrudInterface;
import com.sena.inventarioback.utils.response.DefaultResponse;
import com.sena.inventarioback.utils.response.ObjectResponse;

import reactor.core.publisher.Flux;

public interface IPersonService extends ICrudInterface<Person, PersonDTO,  Integer, PersonRepository> {
	
	List<Person> findByDocumentTypeId(Integer documentTypeId);

	ResponseEntity<com.jdsalasca.defaultresponse.DefaultResponse<Person>> test();
	
	Boolean login(String userName, String password) throws AccountNotFoundException;

	List<String> onConcurrentEndPoint();

	Flux<String> onConcurrentEndPoint2();


}
