package com.example.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
//Interceptor that checks for a specific header before allowing access to certain APIs
public class BetaAccessInterceptor implements HandlerInterceptor {

 // This method runs before the request reaches the controller
 @Override
 public boolean preHandle(HttpServletRequest request, 
                          HttpServletResponse response, 
                          Object handler) throws Exception {

     // Read the value of the custom header "BETA-ACCESS"
     String betaHeader = request.getHeader("BETA-ACCESS");

     // If the header is missing or not equal to "true" (case-insensitive),
     // block the request and return a 403 Forbidden error
     if (!"true".equalsIgnoreCase(betaHeader)) {
         response.sendError(HttpServletResponse.SC_FORBIDDEN,
                            "Access Denied: Beta access required.");
         return false; // Stop further processing
     }

     // If header is valid, allow the request to proceed
     return true;
 }
}

