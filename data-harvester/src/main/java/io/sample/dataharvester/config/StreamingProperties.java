package io.sample.dataharvester.config;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "streaming")
@Data
public class StreamingProperties {
    private String baseUrl;
    private Paths paths;
    private Auth auth;
    private int limitationInSeconds;

    @Data
    public static class Paths {
        private String netflix;
        private String sytazon;
        private String sysney;
    }

    @Data
    public static class Auth {
        private String username;
        private String password;
    }
}