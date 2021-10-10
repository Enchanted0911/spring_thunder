package icu.junyao.security.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import icu.junyao.security.entity.JwtUser;
import icu.junyao.security.properties.JwtProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * jwt工具类
 *
 * @author johnson
 * @date 2021-10-02
 */
@RequiredArgsConstructor
@Component
public class JwtUtil {

    private final JwtProperties jwtProperties;

    /**
     * 根据用户信息生成一个 JWT
     *
     * @return JWT
     */
    public String createAccessToken(JwtUser user) {
        Long accessTokenExpire = jwtProperties.getAccessTokenExpire();
        String key = jwtProperties.getKey();
        return createToken(user, accessTokenExpire, key);
    }


    public String createRefreshToken(JwtUser user) {
        Long refreshTokenExpire = jwtProperties.getRefreshTokenExpire();
        String key = jwtProperties.getRefreshKey();
        return createToken(user, refreshTokenExpire, key);
    }

    public String createToken(JwtUser user, Long expires, String key) {
        Algorithm algorithm = Algorithm.HMAC256(key);
        Date date = new Date(System.currentTimeMillis() + expires);
        return JWT.create()
                .withSubject(user.getUsername())
                .withClaim("authorities", user.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
                .withExpiresAt(date)
                .withIssuedAt(new Date())
                .sign(algorithm);
    }

    public Optional<DecodedJWT> validateToken(HttpServletRequest request) {
        String token = request.getHeader(jwtProperties.getHeader()).replace(jwtProperties.getPrefix(), "");
        String key = jwtProperties.getKey();
        Algorithm algorithm = Algorithm.HMAC256(key);
        JWTVerifier validateToken = JWT.require(algorithm).build();
        try {
            DecodedJWT decodedJwt = validateToken.verify(token);
            return Optional.of(decodedJwt);
        } catch (Exception e) {
            return Optional.empty();
        }
    }
}
