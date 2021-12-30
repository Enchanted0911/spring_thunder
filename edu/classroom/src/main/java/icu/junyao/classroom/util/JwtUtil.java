package icu.junyao.classroom.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import icu.junyao.classroom.entity.User;
import icu.junyao.classroom.properties.JwtProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Optional;

/**
 * jwt工具类
 *
 * @author johnson
 * @date 2021-11-01
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
    public String createAccessToken(User user) {
        Long accessTokenExpire = jwtProperties.getAccessTokenExpire();
        String key = jwtProperties.getKey();
        return createToken(user, accessTokenExpire, key);
    }


    public String createRefreshToken(User user) {
        Long refreshTokenExpire = jwtProperties.getRefreshTokenExpire();
        String key = jwtProperties.getRefreshKey();
        return createToken(user, refreshTokenExpire, key);
    }

    public String createToken(User user, Long expires, String key) {
        Algorithm algorithm = Algorithm.HMAC256(key);
        Date date = new Date(System.currentTimeMillis() + expires);
        return JWT.create()
                .withSubject(user.getMobile())
                .withClaim("id", user.getId())
                .withClaim("nickname", user.getNickname())
                .withExpiresAt(date)
                .withIssuedAt(new Date())
                .sign(algorithm);
    }

    public DecodedJWT validateToken(HttpServletRequest request) {
        if (request.getHeader(jwtProperties.getHeader()) == null) {
            return null;
        }
        String token = request.getHeader(jwtProperties.getHeader()).replace(jwtProperties.getPrefix(), "");
        String key = jwtProperties.getKey();
        Algorithm algorithm = Algorithm.HMAC256(key);
        JWTVerifier validateToken = JWT.require(algorithm).build();
        try {
            return validateToken.verify(token);
        } catch (Exception e) {
            return null;
        }
    }
}
