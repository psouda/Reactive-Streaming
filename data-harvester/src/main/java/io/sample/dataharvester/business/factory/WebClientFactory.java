package io.sample.dataharvester.business.factory;

import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Base64;

@Service
public class WebClientFactory {
    public static final String BASIC = "Basic ";
    public static final String COLUMN = ":";

    public WebClient createWebClient(String baseUrl, String username, String password) {
        return WebClient.builder()
                .baseUrl(baseUrl)
                .defaultHeader(HttpHeaders.AUTHORIZATION, encodeBasicAuth(username, password))
                .build();
    }

    private String encodeBasicAuth(String username, String password) {
        String auth = username + COLUMN + password;
        return BASIC + Base64.getEncoder().encodeToString(auth.getBytes());
    }
}
