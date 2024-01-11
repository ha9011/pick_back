package com.toy.pick.config;

import com.toy.pick.api.service.login.dto.OauthPropertiesDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Map;

@ActiveProfiles("local")
@SpringBootTest
class Oauth2PropertiesTest {

    @Autowired
    private Oauth2PropertiesConfig oauth2Properties;

    @Test
    @DisplayName("")
    void testName() {
        // given
        Map<String, OauthPropertiesDto> oauthProperties = oauth2Properties.getProviders();
        System.out.println("-------????");
        System.out.println(oauthProperties.keySet());
        for (String s : oauthProperties.keySet()) {
            System.out.println("s : " + s);
            System.out.println("to : " + oauthProperties.get(s).toString());
        }

        // when

        // then
    }
}