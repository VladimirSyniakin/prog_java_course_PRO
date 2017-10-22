package com.gmail.vsyniakin.controller;

import com.gmail.vsyniakin.CashFormate;
import com.gmail.vsyniakin.entity.Account;
import com.gmail.vsyniakin.entity.CurrencyType;
import com.gmail.vsyniakin.entity.Transaction;
import com.gmail.vsyniakin.entity.User;
import com.gmail.vsyniakin.query.AccountQuery;
import com.gmail.vsyniakin.query.ExchangeRateQuery;
import com.gmail.vsyniakin.query.TransactionQuery;

import javax.persistence.EntityManager;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class TransactionController {

    public static Transaction putCashToAccount(double cash, Account account, EntityManager em) {
        em.getTransaction().begin();
        cash = CashFormate.formatter(cash);
        Transaction transaction = null;
        if (account != null) {
            try {
                account.setCash(account.getCash() + cash);
                AccountQuery.updateAccount(account, em);
                transaction = new Transaction(0, cash, new Date(), null, account);
                TransactionQuery.addTransactionToDB(transaction, em);
                em.getTransaction().commit();
            } catch (Exception e) {
                em.getTransaction().rollback();
                e.printStackTrace();
            }
        }
        return transaction;
    }

    public static double getCashFromAccount(double cash, Account account, EntityManager em) {
        em.getTransaction().begin();
        cash = CashFormate.formatter(cash);
        if (account != null) {
            try {
                if (account.getCash() >= cash) {
                    account.setCash(account.getCash() - cash);
                    AccountQuery.updateAccount(account, em);
                    TransactionQuery.addTransactionToDB(new Transaction(cash, 0, new Date(), account, null), em);
                    em.getTransaction().commit();
                    return cash;
                }
            } catch (Exception e) {
                em.getTransaction().rollback();
                e.printStackTrace();
            }
        }
        return 0;
    }

    public static Transaction sendCashToAccount(double cashFrom, Account fromAccount, Account toAccount, EntityManager em) {
        Transaction transaction = null;
        cashFrom = CashFormate.formatter(cashFrom);
        try {
            if (fromAccount.getCash() >= cashFrom) {
                double cashTo = CashFormate.formatter(cashFrom * ExchangeRateQuery.currency(fromAccount.getType(), toAccount.getType(), em));
                fromAccount.setCash(fromAccount.getCash() - cashFrom);
                toAccount.setCash(toAccount.getCash() + cashTo);
                em.getTransaction().begin();
                try {
                    AccountQuery.updateAccount(fromAccount, em);
                    AccountQuery.updateAccount(toAccount, em);
                    transaction = new Transaction(cashFrom, cashTo, new Date(), fromAccount, toAccount);
                    TransactionQuery.addTransactionToDB(transaction, em);
                    em.getTransaction().commit();
                } catch (Exception e) {
                    em.getTransaction().rollback();
                    e.printStackTrace();
                }
            } else {
                System.out.println("No money");
            }
        } catch (NullPointerException e) {
            e.printStackTrace();

        }
        return transaction;
    }

    public static Transaction conversionUserCash(User user, double cashFrom, CurrencyType ctFrom, CurrencyType ctTo, EntityManager em) {
        Iterator<Account> iterator = user.getAccounts().iterator();
        Account accountFrom = null;
        Account accountTo = null;
        Account accountTemp;
        Transaction transaction = null;
        while (iterator.hasNext()) {
            accountTemp = iterator.next();
            if (accountTemp.getType().equals(ctFrom)) {
                accountFrom = accountTemp;
            }
            if (accountTemp.getType().equals(ctTo)) {
                accountTo = accountTemp;
            }
        }
        if ((accountFrom != null) && (accountTo != null)) {
            transaction = sendCashToAccount(cashFrom, accountFrom, accountTo, em);
        } else {
            System.out.println("Account not found");
        }
        return transaction;
    }

    public static double getAllCashUser(User user, CurrencyType currencyType, EntityManager em) {
        double cash = 0.0;
        List<Account> accounts = user.getAccounts();
        Account account;
        for (int i = 0; i < accounts.size(); i++) {
            account = accounts.get(i);
            cash = cash + (getCashFromAccount(account.getCash(), account, em) * ExchangeRateQuery.currency(account.getType(), currencyType, em));
        }
        return cash;
    }
}
