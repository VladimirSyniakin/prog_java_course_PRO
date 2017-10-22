package com.gmail.vsyniakin.query;

import com.gmail.vsyniakin.entity.Account;
import com.gmail.vsyniakin.entity.CurrencyType;
import com.gmail.vsyniakin.entity.User;

import javax.persistence.EntityManager;
import javax.persistence.Query;

public class AccountQuery {

    public static void addAccountToDB(Account account, EntityManager em) {
        em.getTransaction().begin();
        try {
            em.persist(account);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        }
    }

    public static Account selectAccountByUser(User user, CurrencyType currencyType, EntityManager em) {
        Query query = em.createNamedQuery("Account.searchByName", Account.class);
        query.setParameter("user", user);
        query.setParameter("type", currencyType);
        Account account;
        try {
            account = (Account) query.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return account;
    }

    public static Account selectAccountById(long id, EntityManager em) {
        return em.find(Account.class, id);
    }

    public static void updateAccount(Account account, EntityManager em) {
        em.merge(account);
    }

}
