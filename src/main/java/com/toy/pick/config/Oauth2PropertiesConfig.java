package com.toy.pick.config;

import com.toy.pick.api.service.login.dto.OauthPropertiesDto;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Getter
@Configuration
@ConfigurationProperties(prefix = "oauth2")
public class Oauth2PropertiesConfig {
    private final Map<String, OauthPropertiesDto> providers = new HashMap<>();
}
