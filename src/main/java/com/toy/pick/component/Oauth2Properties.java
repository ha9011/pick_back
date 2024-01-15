package com.toy.pick.component;

import com.toy.pick.api.service.login.dto.OauthPropertiesDto;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Getter
@Component
@ConfigurationProperties(prefix = "oauth2")
public class Oauth2Properties {
    private final Map<String, OauthPropertiesDto> providers = new HashMap<>();
}
