package com.sena.inventarioback.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
@Primary
public class WebClientConfig {
 /**
  * With the anotation  @LoadBalanced we are adding to the WebClient the possibility to
  * have the capabilities of loadBalancing, in this way the webclient is not get confused when found more than 1 instance
  *
  * @return
  */
	@Bean
	//@LoadBalanced
	 WebClient.Builder webClient () {
		return WebClient.builder();
	}

}
