package org.example.test;

import org.example.metier.Compte;
import org.example.metier.IMetierBanque;
import org.example.metier.IMetierBanqueImpl;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        new Main().start();
    }

    public void start() {
        System.out.println("Demarrage de l'application");
        Scanner scanner = new Scanner(System.in);
        System.out.print("Donner le code du compte : ");
        long code = scanner.nextLong();
        System.out.print("Donner le solde init du compte : ");
        double solde = scanner.nextDouble();

        IMetierBanque metierBanque = new IMetierBanqueImpl();
        metierBanque.addCompte(new Compte(code, solde));

        while(true) {
            try{
                System.out.print("Type Operation:");
                String type = scanner.next();
                if (type.equals("q")) break;

                System.out.print("Montant :");
                double montant = scanner.nextDouble();
                if (type.equals("v")) {
                    metierBanque.verser(code, montant);
                } else if (type.equals("r")) {
                    metierBanque.retirer(code, montant);
                }
                Compte compte = metierBanque.consulter(code);
                System.out.println("Etat du compte :" + compte.toString());

            }catch (Exception e){
                System.out.println(e.getMessage());
            }
        }
        System.out.println("Fin de l'application");

    }
}