package edu.miu.cs.cs544.temuulen.springboot.project.order.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    @Before("execution(* edu.miu.cs.cs544.temuulen.springboot.project.order.service.OrderService.*(..))")
    public void logBefore(JoinPoint joinPoint) {
        System.out.println("AOP Before: " + joinPoint.getSignature().getName());
    }
}
