package icu.junyao.security.filter;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import icu.junyao.common.enums.BusinessResponseEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;

/**
 * 认证过滤器
 *
 * @author johnson
 * @date 2021-10-02
 */
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private ObjectMapper objectMapper;

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager, ObjectMapper objectMapper) {

        super();
        this.objectMapper = objectMapper;
        setAuthenticationManager(authenticationManager);
    }


    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        String usernameSignal = "username";
        String passwordSignal = "password";
        try (InputStream in = request.getInputStream()) {
            JsonNode jsonNode = objectMapper.readTree(in);
            if (!jsonNode.has(usernameSignal) || !jsonNode.has(passwordSignal)) {
                throw new BadCredentialsException("用户名或密码错误!");
            }
            String username = jsonNode.get(usernameSignal).textValue();
            String password = jsonNode.get(passwordSignal).textValue();
            UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(username, password);
            setDetails(request, authRequest);
            return this.getAuthenticationManager().authenticate(authRequest);
        } catch (Exception e) {
            Exception exp = BusinessResponseEnum.PWD_ERROR.newException();
            request.setAttribute("error", exp);
            //将异常分发到/error控制器
            try {
                request.getRequestDispatcher("/error/expire").forward(request, response);
            } catch (ServletException | IOException servletException) {
                servletException.printStackTrace();
            }
        }
        return null;
    }
}
