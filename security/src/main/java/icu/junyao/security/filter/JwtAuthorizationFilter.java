package icu.junyao.security.filter;

import com.auth0.jwt.interfaces.DecodedJWT;
import icu.junyao.security.entity.JwtUser;
import icu.junyao.security.util.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 授权过滤器
 *
 * 确保每次请求都先经过此过滤器
 * 关于哪些资源须要保护哪些资源不须要保护的配置我们在继承WebSecurityConfigurerAdapter的类中配置
 *
 * @author johnson
 * @date 2021-10-02
 */
@Slf4j
public class JwtAuthorizationFilter extends BasicAuthenticationFilter {

    private final JwtUtil jwtUtil;

    private final UserDetailsService userDetailsService;


    public JwtAuthorizationFilter(JwtUtil jwtUtil, AuthenticationManager authenticationManager,UserDetailsService userDetailsService) {
        super(authenticationManager);
        this.jwtUtil = jwtUtil;
        this.userDetailsService = userDetailsService;
    }


    @Override
    public void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        if (checkJwtToken(request)) {
            try {
                jwtUtil.validateToken(request)
                        .ifPresentOrElse(this::setupSpringAuthentication, SecurityContextHolder::clearContext);
            } catch (Exception e) {
                request.setAttribute("error", e);
                //将异常分发到/error控制器
                request.getRequestDispatcher("/error/expire").forward(request, response);
            }
        }
        chain.doFilter(request, response);
    }

    /**
     * 构造 Authentication
     */
    private void setupSpringAuthentication(DecodedJWT decodedJwt) {
        //获取用户信息 重新加载下
        JwtUser jwtUser = (JwtUser) userDetailsService.loadUserByUsername(decodedJwt.getSubject());
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(jwtUser, null, jwtUser.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
    }

    /**
     * 检查 JWT Token 是否在 HTTP 报头中
     *
     * @param req HTTP 请求
     * @return 是否有 JWT Token
     */
    private boolean checkJwtToken(HttpServletRequest req) {
        String authenticationHeader = req.getHeader("Authorization");
        return authenticationHeader != null;
    }
}
