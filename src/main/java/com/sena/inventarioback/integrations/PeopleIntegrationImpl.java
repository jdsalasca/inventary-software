package com.sena.inventarioback.integrations;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.reactive.function.client.WebClient;

import com.sena.inventarioback.exceptions.UserNotFoundException;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@Configuration
@RequiredArgsConstructor
public class PeopleIntegrationImpl implements IPeopleIntegration {

	private final WebClient.Builder webClientBuilder;
	String peopleUrl = System.getenv("PEOPLE_URL");

	@Override
	public Mono<String> onGetUserById(long userId) throws UserNotFoundException {

		return webClientBuilder.build().get().uri(peopleUrl + "people/" + userId).retrieve()
				.onStatus(HttpStatusCode::is4xxClientError, response -> {
					throw new UserNotFoundException("Usuario no encontrado");
				}).bodyToMono(String.class);
	}

}
