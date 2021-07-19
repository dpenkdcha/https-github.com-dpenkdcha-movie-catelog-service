package io.dpenKdcha.movieinfoservice.config;

import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.cloud.client.loadbalancer.reactive.LoadBalancerExchangeFilterFunction;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;


@Configuration
public class RestBuilder {

	@Bean
	public RestTemplate getRestTemplet() {
		return new RestTemplate();		
	}
	
	@Bean
	public WebClient getWebClientBuilder() {
		return WebClient.builder().build();
	}
	
	@Bean
	public WebClient getWebClient(LoadBalancerClient loadBalancerClient) {
		return WebClient.builder()
				.filter(new LoadBalancerExchangeFilterFunction(loadBalancerClient))
				.build();
	}
}
