package com.bitpace.coincalculator.security;

import com.bitpace.coincalculator.ApplicationStatusCodes;
import com.bitpace.coincalculator.fault.ErrorRestResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;

@Slf4j
@Configuration
@EnableWebSecurity
public class SpringSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers(HttpMethod.OPTIONS, "/api/**").permitAll()
                .antMatchers("/api/**").authenticated()
                .anyRequest().permitAll()
                .and().httpBasic().authenticationEntryPoint(apiAwareLoginUrlAuthenticationEntryPoint())
                .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .inMemoryAuthentication()
                .withUser("admin")
                .password(passwordEncoder().encode("Qwer1234"))
                .authorities(Collections.emptyList());
    }

    @Bean
    public ApiBasicAuthenticationEntryPoint apiAwareLoginUrlAuthenticationEntryPoint() {
        ApiBasicAuthenticationEntryPoint entryPoint = new ApiBasicAuthenticationEntryPoint();
        entryPoint.setRealmName("Api Server");
        return entryPoint;
    }

    public static class ApiBasicAuthenticationEntryPoint extends BasicAuthenticationEntryPoint implements AccessDeniedHandler {

        @Autowired
        private ObjectMapper objectMapper;

        @Override
        public void commence(HttpServletRequest request, HttpServletResponse response,
                             AuthenticationException authException) throws IOException {
            log.info("AuthenticationException: {}, {}", authException.getMessage(), authException.getClass());
            response.addHeader("WWW-Authenticate", "Basic realm=\"" + getRealmName() + "\"");
            response.setHeader("Content-Type", "application/json");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            objectMapper.writeValue(response.getOutputStream(),
                    new ErrorRestResponse(ApplicationStatusCodes.ERR_UNAUTHORIZED));
        }

        @Override
        public void handle(HttpServletRequest request, HttpServletResponse response,
                           AccessDeniedException accessDeniedException) throws IOException {
            log.info("AccessDeniedException: {}, {}", accessDeniedException.getMessage(),
                    accessDeniedException.getClass());
            response.setHeader("Content-Type", "application/json");
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            objectMapper.writeValue(response.getOutputStream(),
                    new ErrorRestResponse(ApplicationStatusCodes.ERR_ACCESS_DENIED));
        }
    }

    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
