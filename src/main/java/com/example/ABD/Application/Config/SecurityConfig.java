package com.example.ABD.Application.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private Jswt jswt;

    @Autowired
    private UserDetailsService uds;

    @Bean
    public AuthenticationProvider sdsdd(){

        DaoAuthenticationProvider we=new DaoAuthenticationProvider();

        we.setUserDetailsService(uds);

        we.setPasswordEncoder(new BCryptPasswordEncoder(12));

        return we;


    }

    @Bean
    public SecurityFilterChain dsdsd(HttpSecurity req) throws Exception {

        req.csrf(customizer->customizer.disable()).authorizeHttpRequests(request->request.requestMatchers("register","login").permitAll().anyRequest().authenticated()).httpBasic(Customizer.withDefaults()).sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)).addFilterAfter(jswt, UsernamePasswordAuthenticationFilter.class);

        return req.build();

    }

    @Bean
    public AuthenticationManager sdsd(AuthenticationConfiguration config) throws Exception {

        return config.getAuthenticationManager();

    }

}
