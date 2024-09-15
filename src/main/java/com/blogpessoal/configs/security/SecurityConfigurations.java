package com.blogpessoal.configs.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfigurations {

    @Autowired
    private SecurityFilter securityFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorizeRequests -> authorizeRequests
                        .requestMatchers(HttpMethod.POST,"/auth/register").permitAll()
                        .requestMatchers(HttpMethod.POST,"/auth/login").permitAll()
                        .requestMatchers(HttpMethod.POST,"/post").hasRole("PUBLISHER")
                        .requestMatchers(HttpMethod.POST,"/post").authenticated()
                        .requestMatchers(HttpMethod.POST,"/post/*").hasRole("PUBLISHER")
                        .requestMatchers(HttpMethod.POST,"/post/*").authenticated()
                        .requestMatchers(HttpMethod.POST,"/category/").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST,"/category/*").authenticated()
                        .requestMatchers(HttpMethod.GET,"/users").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET,"/users").authenticated()
                        .requestMatchers(HttpMethod.GET,"/users/*").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET,"/users/*").authenticated()
                        .anyRequest().authenticated())
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
