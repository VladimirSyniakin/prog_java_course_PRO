package com.gmail.vsyniakin.query;

import com.gmail.vsyniakin.CashFormate;
import com.gmail.vsyniakin.entity.CurrencyType;
import com.gmail.vsyniakin.entity.ExchangeRate;

import javax.persistence.EntityManager;
import java.util.Date;

public class ExchangeRateQuery {

    public static void addExchangeRateToDB(ExchangeRate exchangeRate, EntityManager em) {

        em.getTransaction().begin();
        try {
            em.persist(exchangeRate);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        }
    }

    public static void changeExchangeRate(double exchangeRate, CurrencyType from, CurrencyType to, EntityManager em) {
        ExchangeRate er = selectExchangeRate(em);
        exchangeRate = CashFormate.formatter(exchangeRate);
        if (!from.equals(to)) {
            if (from.equals(CurrencyType.UAH)) {
                if (to.equals(CurrencyType.EUR)) {
                    er.setUahToEur(exchangeRate);
                    er.setDate(new Date());
                    em.merge(er);
                } else if (to.equals(CurrencyType.USD)) {
                    er.setUahToUsd(exchangeRate);
                    er.setDate(new Date());
                    em.merge(er);
                }
            } else if (from.equals(CurrencyType.USD)) {
                if (to.equals(CurrencyType.EUR)) {
                    er.setUsdToEur(exchangeRate);
                    er.setDate(new Date());
                    em.merge(er);
                }
            } else {
                changeExchangeRate((1 / exchangeRate), to, from, em);
            }
        } else {
            return;
        }
    }

    public static double currency(CurrencyType from, CurrencyType to, EntityManager em) {
        ExchangeRate er = selectExchangeRate(em);
        if (!from.equals(to)) {
            if (to.equals(CurrencyType.UAH)) {
                if (from.equals(CurrencyType.EUR)) {
                    return CashFormate.formatter(er.getUahToEur());
                } else if (from.equals(CurrencyType.USD)) {
                    return CashFormate.formatter(er.getUahToUsd());
                }
            } else if (to.equals(CurrencyType.USD)) {
                if (from.equals(CurrencyType.EUR)) {
                    return CashFormate.formatter(er.getUsdToEur());
                }
            } else {
                return (1 / currency(to, from, em));
            }
        }
        return 1;
    }

    public static ExchangeRate selectExchangeRate(EntityManager em) {
        return em.find(ExchangeRate.class, 1);
    }
}
