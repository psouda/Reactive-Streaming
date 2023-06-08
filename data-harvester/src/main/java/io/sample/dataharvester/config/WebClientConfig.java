package io.sample.dataharvester.config;

import io.sample.dataharvester.business.factory.WebClientFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    private final StreamingProperties properties;
    private final WebClientFactory webClientFactory;

    public WebClientConfig(StreamingProperties properties, WebClientFactory webClientFactory) {
        this.properties = properties;
        this.webClientFactory = webClientFactory;
    }

    @Bean
    public WebClient webClient() {
        return webClientFactory.createWebClient(properties.getBaseUrl(), properties.getAuth().getUsername(), properties.getAuth().getPassword());
    }
}