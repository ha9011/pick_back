package com.toy.pick.config;

import com.toy.pick.config.Oauth2Properties.Client;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("local")
@SpringBootTest
class Oauth2PropertiesTest {

    @Autowired
    private Oauth2Properties oauth2Properties;

    @Test
    @DisplayName("")
    void testName() {
        // given
        Map<String, Client> user = oauth2Properties.getClient();
        System.out.println("user : " + user.toString());
        Client naver = user.get("naver");

        // when

        // then
    }
}