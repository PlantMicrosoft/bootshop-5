package org.yiqixue.bootshop.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class UserServiceLoggingAspect {
    
    private static final Logger logger = LoggerFactory.getLogger(UserServiceLoggingAspect.class);
    
    @Pointcut("execution(* org.yiqixue.bootshop.service.UserService.saveUser(..))")
    public void saveUserPointcut() {}
    
    @Pointcut("execution(* org.yiqixue.bootshop.service.UserService.updateUser(..))")
    public void updateUserPointcut() {}
    
    @Pointcut("execution(* org.yiqixue.bootshop.service.UserService.deleteUser(..))")
    public void deleteUserPointcut() {}
    
    @Before("saveUserPointcut() || updateUserPointcut() || deleteUserPointcut()")
    public void logBeforeUserModification(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        
        logger.info("Executing method: {} with arguments: {}", methodName, args);
    }

    @AfterReturning(pointcut = "execution(* org.yiqixue.bootshop.service.UserService.getAllUsers(..))")
    public void logAfterUserQuery(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();

        logger.info("Method execution completed: {}", methodName);
    }

    @Before(value = "execution(* org.yiqixue.bootshop.service.UserService.getUserById(..))")
    public void queryByUserIdBefore(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();

        logger.info("Executing method: {} with arguments: {}", methodName, args);
    }
}