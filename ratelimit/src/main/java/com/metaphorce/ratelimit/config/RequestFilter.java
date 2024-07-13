package com.metaphorce.ratelimit.config;

import jakarta.annotation.Nonnull;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class RequestFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(@Nonnull HttpServletRequest request, @Nonnull  HttpServletResponse response, @Nonnull FilterChain filterChain) throws ServletException, IOException {

    }
}
