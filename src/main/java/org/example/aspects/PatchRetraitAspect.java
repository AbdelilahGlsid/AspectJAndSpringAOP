package org.example.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.example.metier.Compte;
import org.example.metier.IMetierBanqueImpl;

@Aspect
public class PatchRetraitAspect {
    @Pointcut("execution(* *..metier.IMetierBanqueImpl.retirer(..))")
    public void pc1(){}

    @Around("pc1() && args(code, montant)")
    public Object autourRetirer(Long code, double montant, ProceedingJoinPoint proceedingJoinPoint, JoinPoint joinPoint) throws Throwable {
        IMetierBanqueImpl metierBanque = (IMetierBanqueImpl) joinPoint.getTarget();
        Compte compte = metierBanque.consulter(code);
        if(compte.getSolde() < montant) throw new RuntimeException("Solde insuffisant");
        return proceedingJoinPoint.proceed();
    }
}
