package com.gmail.vsyniakin;

import com.gmail.vsyniakin.controller.TransactionController;
import com.gmail.vsyniakin.controller.UserController;
import com.gmail.vsyniakin.entity.*;
import com.gmail.vsyniakin.query.AccountQuery;
import com.gmail.vsyniakin.query.ExchangeRateQuery;
import com.gmail.vsyniakin.query.UserQuery;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Date;

public class Main {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("bank");
        EntityManager em = emf.createEntityManager();
        try {

            UserController.addUser("Sasha", "password", em);
            UserController.addUser("Dima", "password", em);
            UserController.addUser("Anya", "password", em);
            ExchangeRateQuery.addExchangeRateToDB(new ExchangeRate(new Date(), 26.54, 31.36, (31.36 / 26.54)), em);

            Transaction transaction = TransactionController.putCashToAccount(247, AccountQuery.selectAccountByUser(UserQuery.selectUserByName("Anya", em), CurrencyType.EUR, em), em);
            System.out.println("Пополнение счета: " + transaction);

            transaction = TransactionController.sendCashToAccount(100, AccountQuery.selectAccountById(8L, em), AccountQuery.selectAccountById(3L, em), em);
            System.out.println("Перевод денег: " + transaction);

            transaction = TransactionController.conversionUserCash(UserQuery.selectUserByName("Anya", em), 100, CurrencyType.EUR, CurrencyType.UAH, em);
            System.out.println("Конвертация денег: " + transaction);

            double cash = TransactionController.getAllCashUser(UserQuery.selectUserByName("Anya", em), CurrencyType.UAH, em);
            System.out.println("Снято денежных средств: " + cash);
            System.out.println(UserQuery.selectUserByName("Anya", em).toString());

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
            emf.close();
        }
    }
}
