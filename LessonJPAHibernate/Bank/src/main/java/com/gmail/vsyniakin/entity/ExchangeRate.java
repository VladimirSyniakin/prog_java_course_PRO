package com.gmail.vsyniakin.entity;

import com.gmail.vsyniakin.CashFormate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import java.util.Date;


import static javax.persistence.TemporalType.TIMESTAMP;

@Entity
public class ExchangeRate {
    @Id
    @GeneratedValue
    private int id;

    @Temporal(TIMESTAMP)
    private Date date;

    private double uahToUsd;
    private double uahToEur;
    private double usdToEur;

    public ExchangeRate() {
    }

    public ExchangeRate(Date date, double uahToUsd, double uahToEur, double usdToEur) {
        this.date = date;
        this.uahToUsd = CashFormate.formatter(uahToUsd);
        this.uahToEur = CashFormate.formatter(uahToEur);
        this.usdToEur = CashFormate.formatter(usdToEur);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getUahToUsd() {
        return uahToUsd;
    }

    public void setUahToUsd(double uahToUsd) {
        this.uahToUsd = uahToUsd;
    }

    public double getUahToEur() {
        return uahToEur;
    }

    public void setUahToEur(double uahToEur) {
        this.uahToEur = uahToEur;
    }

    public double getUsdToEur() {
        return usdToEur;
    }

    public void setUsdToEur(double usdToEur) {
        this.usdToEur = usdToEur;
    }
}
