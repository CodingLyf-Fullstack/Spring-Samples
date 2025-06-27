package com.filter.filtersample;

import java.io.IOException;

import org.springframework.stereotype.Component;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;

@Component
public class CustomFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpReq = (HttpServletRequest) request;

		long startTime = System.currentTimeMillis();
		System.out.println("Request URI: " + httpReq.getRequestURI() +" Before the request");

		chain.doFilter(request, response);
		long duration = System.currentTimeMillis() - startTime;
		System.out.println("Request URI: " + httpReq.getRequestURI() + ", After the request and Time taken: " + duration + "ms");

		

	}

}
