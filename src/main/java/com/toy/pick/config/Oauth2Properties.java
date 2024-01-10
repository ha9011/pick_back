package com.toy.pick.config;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Getter
@Configuration
@ConfigurationProperties(prefix = "oauth2")
public class Oauth2Properties {
    private final Map<String, Client> client = new HashMap<>();

    private final Map<String, Provider> provider = new HashMap<>();

    @Getter
    @Setter
    @ToString
    public static class Client {
        private String clientId;
        private String clientSecret;
        private String redirectUri;
    }

    @Getter
    @Setter
    @ToString
    public static class Provider {
        private String tokenUri;
        private String userInfoUri;
        private String userNameAttribute;
    }

}
