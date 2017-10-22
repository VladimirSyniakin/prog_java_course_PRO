package com.gmail.vsyniakin.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "users")
@NamedQueries({
        @NamedQuery(name = "User.searchByName", query = "SELECT u FROM User u WHERE u.name = :name")
})
public class User {
    @Id
    @GeneratedValue
    private long id;
    private String name;
    private String password;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Account> accounts = new ArrayList<>();

    public void addAccount(Account account) {
        account.setUser(this);
        accounts.add(account);

    }

    public User() {
    }

    public User(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }

    @Override
    public String toString() {
        return "User{" +
                ", name='" + name + '\'' +
                ", accounts=" + accounts +
                '}';
    }
}
