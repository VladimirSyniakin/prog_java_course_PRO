package com.gmail.vsyniakin.entity;

import javax.persistence.*;
import java.util.Date;

import static javax.persistence.TemporalType.TIMESTAMP;

@Entity
@Table(name = "transactions")
public class Transaction {
    @Id
    @GeneratedValue
    private int id;

    private double cashFrom;
    private double cashTo;

    @Temporal(TIMESTAMP)
    private Date date;

    @OneToOne
    private Account fromAcc;

    @OneToOne
    private Account toAcc;

    public Transaction() {
    }

    public Transaction(double cashFrom, double cashTo, Date date, Account fromAcc, Account toAcc) {
        this.cashFrom = cashFrom;
        this.cashTo = cashTo;
        this.date = date;
        this.fromAcc = fromAcc;
        this.toAcc = toAcc;
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

    public Account getFromAcc() {
        return fromAcc;
    }

    public void setFromAcc(Account fromAcc) {
        this.fromAcc = fromAcc;
    }

    public double getCashFrom() {
        return cashFrom;
    }

    public void setCashFrom(double cashFrom) {
        this.cashFrom = cashFrom;
    }

    public double getCashTo() {
        return cashTo;
    }

    public void setCashTo(double cashTo) {
        this.cashTo = cashTo;
    }

    public Account getToAcc() {
        return toAcc;
    }

    public void setToAcc(Account toAcc) {
        this.toAcc = toAcc;
    }

    @Override
    public String toString() {
        if ((fromAcc != null) && (toAcc != null)) {
            return "Transaction{" +
                    "id=" + id +
                    ", cashFrom=" + cashFrom +
                    ", cashTo=" + cashTo +
                    ", date=" + date +
                    ", fromAcc=" + fromAcc.getType() +
                    ", fromUser=" + fromAcc.getUser().getName() +
                    ", toAcc=" + toAcc.getType() +
                    ", toUser=" + toAcc.getUser().getName() +
                    '}';
        } else if (fromAcc == null) {
            return "Transaction{" +
                    "id=" + id +
                    ", cashTo=" + cashTo +
                    ", date=" + date +
                    ", toAcc=" + toAcc.getType() +
                    ", toUser=" + toAcc.getUser().getName() +
                    '}';
        } else {
            return "Transaction{" +
                    "id=" + id +
                    ", cashFrom=" + cashFrom +
                    ", date=" + date +
                    ", fromAcc=" + fromAcc.getType() +
                    ", fromUser=" + fromAcc.getUser().getName() +
                    '}';
        }
    }
}
