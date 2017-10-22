package com.gmail.vsyniakin.entity;


import com.gmail.vsyniakin.CashFormate;

import javax.persistence.*;

@Entity
@Table(name = "accounts")
@NamedQueries({
        @NamedQuery(name = "Account.searchByName", query = "SELECT a FROM Account a WHERE a.user = :user AND a.type = :type")
})
public class Account {
    @Id
    @GeneratedValue
    private long id;
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    private User user;
    @Enumerated(EnumType.STRING)
    private CurrencyType type;
    private double cash;

    public Account() {
    }

    public Account(User user, CurrencyType type, double cash) {
        this.user = user;
        this.type = type;
        this.cash = cash;
    }

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public CurrencyType getType() {
        return type;
    }

    public void setType(CurrencyType type) {
        this.type = type;
    }

    public double getCash() {
        return cash;
    }

    public void setCash(double cash) {
        this.cash = CashFormate.formatter(cash);
        ;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", user=" + user.getName() +
                ", type=" + type +
                ", cash=" + cash +
                '}';
    }
}
