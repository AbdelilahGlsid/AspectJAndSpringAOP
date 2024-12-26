package org.example.aspects;

import jdk.jpackage.internal.Log;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;

@Aspect
public class LogginAspect {
    Logger logger = Logger.getLogger(LogginAspect.class.getName());
    //long t1, t2;

    public LogginAspect() throws IOException {
        logger.addHandler(new FileHandler("log.xml"));
        logger.setUseParentHandlers(false);
    }

    @Pointcut("execution(* *..metier.IMetierBanqueImpl.*(..)) || initialization( *..metier.IMetierBanqueImpl.new(..))")
    public void pc1(){}

    /*@Before("pc1()")
    public void avant(JoinPoint joinPoint){
        t1 = System.currentTimeMillis();
        logger.info("-----------------------------------------------");
        logger.info("Avant l'execution de la methode "+joinPoint.getSignature());
    }

    @After("pc1()")
    public void apres(JoinPoint joinPoint){
        logger.info("Apres l'execution de la methode "+joinPoint.getSignature());
        t2 = System.currentTimeMillis();
        logger.info("Durée d'execution de la methode "+(t2-t1));
        logger.info("-----------------------------------------------");
    }*/

    @Around("pc1()")
    public Object autour(ProceedingJoinPoint proceedingJoinPoint, JoinPoint joinPoint) throws Throwable {
        long t1 = System.currentTimeMillis();
        logger.info("-----------------------------------------------");
        logger.info("Avant l'execution de la methode "+joinPoint.getSignature());

        Object result = proceedingJoinPoint.proceed();

        logger.info("Apres l'execution de la methode "+joinPoint.getSignature());
        long t2 = System.currentTimeMillis();
        logger.info("Durée d'execution de la methode "+(t2-t1));
        logger.info("-----------------------------------------------");

        return result;
    }

}
