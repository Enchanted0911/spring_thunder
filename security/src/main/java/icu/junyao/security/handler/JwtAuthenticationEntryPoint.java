package icu.junyao.security.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import icu.junyao.common.entity.R;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Optional;

/**
 * @author johnson
 * @date 2021-10-02
 */
@Component
@RequiredArgsConstructor
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    private final ObjectMapper objectMapper;

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException {
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding("UTF-8");
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        PrintWriter writer = response.getWriter();
        String error = Optional.ofNullable(request.getAttribute("errorMessage")).orElse(
                "禁止访问"
        ).toString();
        writer.append(objectMapper.writeValueAsString(R.fail(error)));
    }

}

