package com.example.aop;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    // Pointcut for all methods inside UserService
    @Pointcut("execution(* com.example.aop.UserService.*(..))")
    public void userServiceMethods() {}

    // Before advice
    @Before("userServiceMethods()")
    public void logBefore(JoinPoint joinPoint) {
        System.out.println("Before method: " + joinPoint.getSignature().getName() +
                " | Args: " + Arrays.toString(joinPoint.getArgs()));
    }

    // After returning advice
    @AfterReturning(value = "userServiceMethods()", returning = "result")
    public void logAfterReturning(JoinPoint joinPoint, Object result) {
        System.out.println("After method: " + joinPoint.getSignature().getName() +
                " | Returned: " + result);
    }

    // After throwing advice
    @AfterThrowing(value = "userServiceMethods()", throwing = "ex")
    public void logAfterThrowing(JoinPoint joinPoint, Exception ex) {
        System.out.println("Exception in method: " + joinPoint.getSignature().getName() +
                " | Exception: " + ex.getMessage());
    }

    // Around advice
    @Around("userServiceMethods()")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("Around (Before): " + joinPoint.getSignature().getName());
        Object result = joinPoint.proceed();
        System.out.println("Around (After): " + joinPoint.getSignature().getName());
        return result;
    }
}

