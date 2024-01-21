package com.toy.pick.api.service.login;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.toy.pick.api.ApiResponse;
import com.toy.pick.api.service.login.dto.OauthPropertiesDto;
import com.toy.pick.api.service.login.dto.UserInfo;
import com.toy.pick.api.service.login.response.JwtTokenRes;
import com.toy.pick.api.service.sns.SnsLoginService;
import com.toy.pick.component.JwtTokenProvider;
import com.toy.pick.component.Oauth2Properties;
import com.toy.pick.domain.user.Member;
import com.toy.pick.domain.user.MemberRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.util.ReflectionTestUtils;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
class LoginServiceTest {

    @InjectMocks
    private LoginService loginService;

    @Mock
    private Oauth2Properties oauth2Properties;

    @Mock
    private ObjectMapper om;

    @Spy
    private JwtTokenProvider jwtTokenProvider;

    @Mock
    private MemberRepository memberRepository;

    @Mock
    private SnsLoginService snsLoginService;

//    @Value("${jwt.access-token.expire-time}")
//    private long accessTokenValidTime;
//    @Value("${jwt.refresh-token.expire-time}")
//    private long refreshTokenValidTime; // 2주
//    @Value("${jwt.secret.key}")
//    private String scretkey = "jwtTestKeyjwtTestKeyjwtTestKeyjwtTestKeyjwtTestKeyjwtTestKey";
//
//    @BeforeEach
//    void setup() {
//        System.out.println("scretkey tt : "+ scretkey);
//        // 리플렉션을 사용하여 @Value로 주입된 필드의 값을 변경
//        ReflectionTestUtils.setField(jwtTokenProvider, "accessTokenValidTime", accessTokenValidTime);
//        ReflectionTestUtils.setField(jwtTokenProvider, "refreshTokenValidTime", refreshTokenValidTime);
//        ReflectionTestUtils.setField(jwtTokenProvider, "secretKey", "123123123123123123123123123123123123123123");
//    }

    @Test
    @DisplayName("소셜로그인 접속 후 Code를 전달받아, pick에 이용할 access&refresh Token을 만든다.")
    void loginSnsOauth() throws JsonProcessingException {
        // given
        String provider = "naver";
        String code = "naver_code";


        OauthPropertiesDto oauthProperties = new OauthPropertiesDto(
                "clientId",
                "clientSecret",
                "redirectUri",
                "tokenUri",
                "userInfoUri"
        );
        Map<String, OauthPropertiesDto> oauth2PropertieMap = Map.of("naver",oauthProperties);

        String snsAccessToken = "sns_access_token";
        String accessToken = "accessToken";
        String refreshToken = "refreshToken";
        UserInfo userInfo = UserInfo.of("hadong", provider);

        // when
        when(oauth2Properties.getProviders()).thenReturn(oauth2PropertieMap);
        when(snsLoginService.getSnsAccessToken(eq(code), eq(oauthProperties))).thenReturn(snsAccessToken);
        when(snsLoginService.getSnsUserInfo(eq(snsAccessToken), eq(oauthProperties), eq(provider))).thenReturn(userInfo);
        doReturn(accessToken).when(jwtTokenProvider).createAccessToken(userInfo);
        doReturn(refreshToken).when(jwtTokenProvider).createRefreshToken(userInfo);
        when(memberRepository.findByUserId(eq("hadong"))).thenReturn(new Member("","","","",""));

        JwtTokenRes jwtTokenRes = loginService.loginSnsOauth(provider, code);
        // then
        assertThat(jwtTokenRes)
                .extracting("accessToken","refreshToken")
                .contains("accessToken","refreshToken");
    }

    @Test
    @DisplayName("AccessToken에서 Payload 정보 가져오기")
    void userTokenInfo() {
        // given


        // 유저 생성
        UserInfo userInfo = UserInfo.of("test_id", "naver");
        String accessToken = jwtTokenProvider.createAccessToken(userInfo);
        //loginService.loginSnsOauth("");
        System.out.println("accessToken : " + accessToken);
        // when


        // then
    }
}