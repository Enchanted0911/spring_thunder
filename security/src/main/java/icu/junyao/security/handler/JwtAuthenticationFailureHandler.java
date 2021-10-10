package icu.junyao.security.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import icu.junyao.common.entity.R;
import icu.junyao.common.enums.ResultCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.ServletException;
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
public class JwtAuthenticationFailureHandler implements AuthenticationFailureHandler {

    private final ObjectMapper objectMapper;

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException {
        R<String> result = new R<>();
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        result.setCode(ResultCode.OAUTH_ERROR.getCode());
        response.setCharacterEncoding("UTF-8");
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        PrintWriter writer = response.getWriter();
        writer.append(objectMapper.writeValueAsString(R.fail(e.getMessage())));
    }
}
