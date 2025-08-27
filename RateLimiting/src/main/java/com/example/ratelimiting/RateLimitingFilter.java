package com.example.ratelimiting;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.stereotype.Component;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class RateLimitingFilter implements Filter {

    private final Map<String, AtomicInteger> requestCountsPerIpAddress = new ConcurrentHashMap<>();
    private static final int MAX_REQUESTS_PER_MINUTE = 5;

    public RateLimitingFilter() {
        // Schedule reset every 1 minute
        Executors.newSingleThreadScheduledExecutor().scheduleAtFixedRate(
                requestCountsPerIpAddress::clear, 
                1, 1, TimeUnit.MINUTES
        );
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;

        String clientIpAddress = httpServletRequest.getRemoteAddr();

        requestCountsPerIpAddress.putIfAbsent(clientIpAddress, new AtomicInteger(0));
        AtomicInteger requestCount = requestCountsPerIpAddress.get(clientIpAddress);

        System.out.println(clientIpAddress);
        System.err.println(requestCount);
        int requests = requestCount.incrementAndGet();

        if (requests > MAX_REQUESTS_PER_MINUTE) {
            httpServletResponse.setStatus(429);
            httpServletResponse.getWriter().write("Too many requests. Please try again later.");
            return;
        }

        chain.doFilter(request, response);
    }
}
