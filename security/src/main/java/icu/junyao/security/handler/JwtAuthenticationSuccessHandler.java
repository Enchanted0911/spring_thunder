package icu.junyao.security.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import icu.junyao.security.Res.LoginRes;
import icu.junyao.security.entity.JwtUser;
import icu.junyao.security.properties.JwtProperties;
import icu.junyao.security.util.JwtUtil;
import icu.junyao.common.entity.R;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author johnson
 * @date 2021-10-02
 */
@Slf4j
@RequiredArgsConstructor
public class JwtAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private final JwtUtil jwtUtil;
    private final ObjectMapper objectMapper;
    private final JwtProperties jwtProperties;


    @Override
    public void onAuthenticationSuccess(HttpServletRequest req, HttpServletResponse response, Authentication authentication) throws IOException {
        JwtUser jwtUser = (JwtUser) authentication.getPrincipal();
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding("UTF-8");
        response.setStatus(HttpStatus.OK.value());
        PrintWriter writer = response.getWriter();
        LoginRes loginRes = new LoginRes();
        loginRes.setAccessToken(jwtUtil.createAccessToken(jwtUser));
        loginRes.setRefreshToken(jwtUtil.createRefreshToken(jwtUser));
        loginRes.setExpiresIn(jwtProperties.getAccessTokenExpire());
        BeanUtils.copyProperties(jwtUser, loginRes);
        writer.write(objectMapper.writeValueAsString(R.data(loginRes)));
    }
}
