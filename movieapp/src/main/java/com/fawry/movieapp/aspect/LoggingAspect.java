package com.fawry.movieapp.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
@Slf4j
@Aspect
@Component
public class LoggingAspect {

    @Before("execution(* com.fawry.movieapp..*(..))")
    public void logBefore(JoinPoint joinPoint) {
        log.info("Calling method: {}", joinPoint.getSignature().getName());
    }

    @AfterReturning(
            pointcut = "execution(* com.fawry.movieapp..*(..))",
            returning = "result"
    )
    public void logAfter(JoinPoint joinPoint , Object result) {
        log.info("Method {} returned: {}", joinPoint.getSignature().getName(), result);
    }
}
