package com.example.ABD.Application.Config;

import com.example.ABD.Application.Service.KeyService;
import com.example.ABD.Application.Service.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class Jswt extends OncePerRequestFilter{

    @Autowired
    private KeyService jswts;

    @Autowired
    ApplicationContext context;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String auth=request.getHeader("Authorization");

        String username=null;

        String token=null;

        if(auth!=null && auth.startsWith("Bearer ")){

            token=auth.substring(7);

            username=jswts.extractUserName(token);

        }

        if (username!=null && SecurityContextHolder.getContext().getAuthentication()==null){

            UserDetails ud=context.getBean(UserService.class).loadUserByUsername(username);

            if (jswts.validateToken(token,ud)){

                UsernamePasswordAuthenticationToken us=new UsernamePasswordAuthenticationToken(ud,null,ud.getAuthorities());

                us.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                SecurityContextHolder.getContext().setAuthentication(us);


            }


        }
        filterChain.doFilter(request,response);


    }
}
