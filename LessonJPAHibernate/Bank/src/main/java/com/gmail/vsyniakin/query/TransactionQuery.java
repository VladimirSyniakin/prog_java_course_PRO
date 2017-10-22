package com.gmail.vsyniakin.query;

import com.gmail.vsyniakin.entity.Transaction;

import javax.persistence.EntityManager;

public class TransactionQuery {
    public static void addTransactionToDB(Transaction transaction, EntityManager em) {
        try {
            em.persist(transaction);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
