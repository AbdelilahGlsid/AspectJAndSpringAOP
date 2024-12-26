package org.example.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

import java.util.Scanner;

@Aspect
public class SecurityAspect {
    @Pointcut("execution(* *..test.Main.start(..))")
    public void startAppPoincut(){}

    @Around("startAppPoincut()")
    public void autourStart(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        Scanner scanner=new Scanner(System.in);
        System.out.print("Username:");
        String username=scanner.next();
        System.out.print("Password:");
        String password=scanner.next();
        if(username.equals("abdelilah") && password.equals("1234")) {
            proceedingJoinPoint.proceed();
        }
        else{
            System.out.println("Access Denied ...");
        }
    }
}
