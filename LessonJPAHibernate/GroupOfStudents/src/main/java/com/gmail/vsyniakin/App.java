package com.gmail.vsyniakin;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Scanner;

public class App {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("groupsDb");;
        EntityManager em = emf.createEntityManager();;
        try (Scanner scanner = new Scanner(System.in);){

            ConsoleMenu.getAction(scanner, em);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
            emf.close();
        }
    }
}
