package com.jwt.jwt.config;
import com.jwt.jwt.service.auth.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
@Component //a spring bean
@RequiredArgsConstructor
public class JwtAthenticationFilter extends OncePerRequestFilter {
   private  final JwtService jwtService;
    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response,@NonNull FilterChain filterChain) throws ServletException, IOException {
        final String authHeaader = request.getHeader("Auhtorization");
        final String jwt;
        final String username;
        if(authHeaader == null || authHeaader.startsWith("Bearer")){
            filterChain.doFilter(request,response);
            return;
        }
        jwt = authHeaader.substring(7);
        username = jwtService.extractUsername(jwt);
    }
}
