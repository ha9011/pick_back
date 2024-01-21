package com.toy.pick.component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.toy.pick.api.service.login.dto.UserInfo;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import java.security.Key;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
@Slf4j
public class JwtTokenProvider {
    @Value("${jwt.access-token.expire-time}")
    private long accessTokenValidTime;
    @Value("${jwt.refresh-token.expire-time}")
    private long refreshTokenValidTime; // 2주
    @Value("${jwt.secret.key}")
    private String scretkey;
    private Key key;

    private ObjectMapper om = new ObjectMapper();


    public String createAccessToken(UserInfo payload) {
        System.out.println("accessTokenValidTime : " + accessTokenValidTime);
        LocalDateTime expirationDateTime = LocalDateTime.now().plusHours(accessTokenValidTime);
         return createToken(payload, expirationDateTime);
    }

    public String createRefreshToken(UserInfo payload) {
        LocalDateTime expirationDateTime = LocalDateTime.now().plusDays(refreshTokenValidTime);
        return createToken(payload, expirationDateTime);
    }

    public String createToken(UserInfo userInfo, LocalDateTime expirationDateTime) {
        System.out.println("scretkey : " + scretkey);
        byte[] keyBytes = Decoders.BASE64.decode(scretkey);
        this.key = Keys.hmacShaKeyFor(keyBytes);

        Map<String, String> claims = new HashMap<>();
        claims.put("userId", userInfo.getUserId());
        claims.put("provider", userInfo.getProvider());

        Date expirationDate = Date.from(expirationDateTime.atZone(ZoneId.systemDefault()).toInstant());
        Date now = new Date();
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(expirationDate)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    public String validateAndRefreshAccessToken(String refreshToken) {
        // Refresh Token의 유효성 검증
        if (this.validateToken(refreshToken)) {
            // Refresh Token이 유효하면 새로운 Access Token 발급
            UserInfo allPayload = getAllPayload(refreshToken);
            return createAccessToken(allPayload);
        } else {
            // Refresh Token이 유효하지 않으면 예외 처리 또는 다른 처리 수행
            throw new JwtException("유효하지 않은 토큰입니다. 다시 로그인해 주세요.");
        }
    }

    public UserInfo getAllPayload(String token) {

        String tokenWithoutBearer = token.replace("Bearer ", "");

        try {
            Claims claims = Jwts.parserBuilder().setSigningKey(scretkey).build().parseClaimsJws(tokenWithoutBearer).getBody();
            String payloadJson = om.writeValueAsString(claims);
            return om.readValue(payloadJson, UserInfo.class);
        } catch (JwtException e) {
            throw new RuntimeException("유효하지 않은 토큰입니다. 다시 로그인해 주세요."); // TODO customException 만들기
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
    public String getJwtPayloadUserId(String token) {
        String tokenWithoutBearer = token.replace("Bearer ", "");

        try {
            return (String) Jwts.parserBuilder().setSigningKey(scretkey).build().parseClaimsJws(tokenWithoutBearer).getBody().get("userId");
        } catch (JwtException e) {
            throw new JwtException("유효하지 않은 토큰입니다. 다시 로그인해 주세요.");
        }
    }
    public String getJwtPayloadProvider(String token) {
        String tokenWithoutBearer = token.replace("Bearer ", "");

        try {
            return (String) Jwts.parserBuilder().setSigningKey(scretkey).build().parseClaimsJws(tokenWithoutBearer).getBody().get("provider");
        } catch (JwtException e) {
            throw new JwtException("유효하지 않은 토큰입니다. 다시 로그인해 주세요.");
        }
    }

    public boolean validateToken(String token) {
        try {
            Jws<Claims> claims = Jwts.parserBuilder().setSigningKey(scretkey).build().parseClaimsJws(token);
            return !claims.getBody().getExpiration().before(new Date());
        } catch(ExpiredJwtException e){
            return false;
        }catch ( JwtException | IllegalArgumentException e) {
            throw new JwtException("유효하지 않은 토큰입니다. 다시 로그인해 주세요.");
        }
    }
}
