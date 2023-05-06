package com.sena.inventarioback.integrations;

import java.util.Optional;

import reactor.core.publisher.Mono;

public interface IPeopleIntegration {
	
	public Mono<String> onGetUserById(long userId);
	

}

