package icu.junyao.security.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import icu.junyao.security.filter.JwtAuthenticationFilter;
import icu.junyao.security.filter.JwtAuthorizationFilter;
import icu.junyao.security.handler.*;
import icu.junyao.security.properties.JwtProperties;
import icu.junyao.security.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/**
 * @author johnson
 * @date 2021-10-02
 */
@EnableWebSecurity
@Slf4j
@RequiredArgsConstructor
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private final JwtAccessDeniedHandler jwtAccessDeniedHandler;
    private final ObjectMapper objectMapper;
    private final JwtUtil jwtUtil;
    private final JwtProperties jwtProperties;
    private final UserDetailsService userDetailsService;


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf(AbstractHttpConfigurer::disable)
                .exceptionHandling(exceptionHandling -> exceptionHandling
//                .authenticationEntryPoint(jwtAuthenticationEntryPoint)
                        .accessDeniedHandler(jwtAccessDeniedHandler))
                .authorizeRequests(authorizeRequests -> authorizeRequests
                        .antMatchers("/v2/api-docs", "/swagger-resources",
                                "/favicon.ico", "/webjars/**", "/doc.html", "/rabbit/acl/login", "/**/export-excel").permitAll().anyRequest().authenticated())
                .logout(logout -> logout.logoutRequestMatcher(new AntPathRequestMatcher("/rabbit/acl/logout", "POST"))
                        .logoutSuccessHandler(new JwtLogoutSuccessHandler(objectMapper)))
                .addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(new JwtAuthorizationFilter(jwtUtil, authenticationManager(), userDetailsService), UsernamePasswordAuthenticationFilter.class)
                .formLogin(AbstractHttpConfigurer::disable)
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }


    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter() throws Exception {
        JwtAuthenticationFilter filter = new JwtAuthenticationFilter(authenticationManager(), objectMapper);
        filter.setAuthenticationSuccessHandler(new JwtAuthenticationSuccessHandler(jwtUtil, objectMapper, jwtProperties));
        filter.setAuthenticationFailureHandler(new JwtAuthenticationFailureHandler(objectMapper));
        filter.setAuthenticationManager(super.authenticationManager());
        filter.setFilterProcessesUrl("/rabbit/acl/login");
        return filter;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
